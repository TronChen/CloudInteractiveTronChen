package com.tron.cloudinteractivetronchen.second

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tron.cloudinteractivetronchen.R
import com.tron.cloudinteractivetronchen.data.*
import com.tron.cloudinteractivetronchen.networks.LoadApiStatus
import com.tron.cloudinteractivetronchen.networks.PicApi
import com.tron.cloudinteractivetronchen.util.Utils.getString
import kotlinx.coroutines.*
import java.io.IOException
import java.io.InputStream
import java.net.URL


class SecondViewModel(
    private val cloudRepository: CloudRepository
) : ViewModel() {

    // status: The internal MutableLiveData that stores the status of the most recent request
    private val _photo = MutableLiveData<List<Photo>>()

    val photo: LiveData<List<Photo>>
        get() = _photo

    val list = mutableListOf<Photo>()

    // status: The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    // error: The internal MutableLiveData that stores the error of the most recent request
    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    // status for the loading icon of swl
    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    // Handle navigation to detail
    private val _navigateToDetail = MutableLiveData<Photo>()

    val navigateToDetail: LiveData<Photo>
        get() = _navigateToDetail

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val coroutineScope2 = CoroutineScope(viewModelJob + Dispatchers.IO)

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    init {
        getPhotos(true)
    }

    private fun getPhotos(isInitial: Boolean = false) {

        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING

            val result = cloudRepository.getPhotos()
            Log.d("Tron", "${result.succeeded}")
            _photo.value = when (result) {
                is AppResult.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    result.data
                }
                is AppResult.Fail -> {
                    _error.value = result.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is AppResult.Error -> {
                    _error.value = result.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false
        }
    }


    fun returnBitmap(photo: Photo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                photo.thumbnailUrl?.let {
                    urlToBitmap(it)
                }
            }.let {
                photo.bitmap = it
            }
        }
    }

    private fun urlToBitmap (url: String): Bitmap? {

        var bitmap: Bitmap? = null
        try {
            val conn = URL(url).openConnection().apply {
                setRequestProperty("User-Agent", "custom-agent")
            }
            bitmap = BitmapFactory.decodeStream(conn.getInputStream())
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("SSSS","AKAKAKA")
        }
            return bitmap
    }

}