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

    override suspend fun saveBitmapToCache(key: String, bitmap: Bitmap){
        return cloudLocalDataSource.saveBitmapToCache(key, bitmap)
    }

    override suspend fun retrieveBitmapFromCache(key: String): Bitmap?{
        return cloudLocalDataSource.retrieveBitmapFromCache(key)
    }
}
