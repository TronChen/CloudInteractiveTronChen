package com.tron.cloudinteractivetronchen.data.local

import android.content.Context
import android.graphics.Bitmap
import com.tron.cloudinteractivetronchen.data.AppResult
import com.tron.cloudinteractivetronchen.data.CloudDataSource
import com.tron.cloudinteractivetronchen.data.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CloudLocalDataSource(val context: Context) : CloudDataSource {
    override suspend fun getPhotos(): AppResult<List<Photo>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveBitmapToCache(key: String, bitmap: Bitmap) {
        withContext(Dispatchers.IO) {
            Cache.instance.saveBitmapToCache(key, bitmap)
        }
    }

    override suspend fun retrieveBitmapFromCache(key: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            Cache.instance.retrieveBitmapFromCache(key)
        }
    }
}
