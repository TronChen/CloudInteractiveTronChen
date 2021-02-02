package com.tron.cloudinteractivetronchen.data.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.tron.cloudinteractivetronchen.R
import com.tron.cloudinteractivetronchen.data.AppResult
import com.tron.cloudinteractivetronchen.data.CloudDataSource
import com.tron.cloudinteractivetronchen.data.Photo
import com.tron.cloudinteractivetronchen.networks.CloudApi
import com.tron.cloudinteractivetronchen.second.SecondViewModel
import com.tron.cloudinteractivetronchen.util.Utils.getString
import com.tron.cloudinteractivetronchen.util.Utils.isInternetConnected
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object CloudRemoteDataSource : CloudDataSource {

    override suspend fun getPhotos(): AppResult<List<Photo>> {
        if (!isInternetConnected()) {
            return AppResult.Fail(getString(R.string.internet_not_connected))
        }

        return try {
            // this will run on a thread managed by Retrofit
            val listResult = CloudApi.retrofitService.getPhotos()
            return AppResult.Success(listResult)

        } catch (e: Exception) {
            Log.e("Tron", "[${this::class.simpleName}] exception=${e.message}")
            AppResult.Error(e)
        }
    }

    override suspend fun saveBitmapToCache(key: Int, bitmap: Bitmap) {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveBitmapFromCache(key: Int): Bitmap? {
        TODO("Not yet implemented")
    }
}
