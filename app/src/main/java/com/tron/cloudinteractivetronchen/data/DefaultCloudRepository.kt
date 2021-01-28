package com.tron.cloudinteractivetronchen.data

import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


class DefaultCloudRepository(private val cloudRemoteDataSource: CloudDataSource,
                             private val cloudLocalDataSource: CloudDataSource,
                             private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CloudRepository {

    override suspend fun getPhotos(): AppResult<List<Photo>>{
        return cloudRemoteDataSource.getPhotos()
    }

    override suspend fun getBitmapFromURL(src: String?): Bitmap? {
        return cloudRemoteDataSource.getBitmapFromURL(src)
    }
}
