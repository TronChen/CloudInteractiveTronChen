package com.tron.cloudinteractivetronchen.data.remote

import android.provider.Settings.Global.getString
import android.util.Log
import com.tron.cloudinteractivetronchen.R
import com.tron.cloudinteractivetronchen.data.AppResult
import com.tron.cloudinteractivetronchen.data.CloudDataSource
import com.tron.cloudinteractivetronchen.data.Photo
import com.tron.cloudinteractivetronchen.networks.CloudApi
import com.tron.cloudinteractivetronchen.util.Utils.getString
import com.tron.cloudinteractivetronchen.util.Utils.isInternetConnected

object CloudRemoteDataSource : CloudDataSource {

    override suspend fun getPhotos(): AppResult<List<Photo>> {
        if (!isInternetConnected()) {
            return AppResult.Fail(getString(R.string.internet_not_connected))
        }

        return try {
            // this will run on a thread managed by Retrofit
            val listResult = CloudApi.retrofitService.getPhotos()

//            listResult.error?.let {
//                return AppResult.Fail(it)
//            }
            return AppResult.Success(listResult)

        } catch (e: Exception) {
            Log.e("Tron", "[${this::class.simpleName}] exception=${e.message}")
            AppResult.Error(e)
        }
    }
}
