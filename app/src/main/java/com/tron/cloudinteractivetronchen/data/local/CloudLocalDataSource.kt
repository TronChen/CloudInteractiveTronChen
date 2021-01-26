package com.tron.cloudinteractivetronchen.data.local

import android.content.Context
import com.tron.cloudinteractivetronchen.data.AppResult
import com.tron.cloudinteractivetronchen.data.CloudDataSource
import com.tron.cloudinteractivetronchen.data.Photo


class CloudLocalDataSource(val context: Context) : CloudDataSource {
    override suspend fun getPhotos(): AppResult<List<Photo>> {
        TODO("Not yet implemented")
    }

}
