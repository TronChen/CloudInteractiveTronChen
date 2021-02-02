package com.tron.cloudinteractivetronchen.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tron.cloudinteractivetronchen.data.CloudRepository
import com.tron.cloudinteractivetronchen.data.Photo

class ThirdViewModel(
    private val cloudRepository: CloudRepository,
    photo: Photo
) : ViewModel() {

    val _photo = MutableLiveData<Photo>()
    val photo : LiveData<Photo>
    get() = _photo


    init {
        _photo.value = photo
    }

}