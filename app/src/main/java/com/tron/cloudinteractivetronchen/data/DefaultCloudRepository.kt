package com.tron.cloudinteractivetronchen.data

import android.graphics.Bitmap



class DefaultCloudRepository(private val cloudRemoteDataSource: CloudDataSource,
                             private val cloudLocalDataSource: CloudDataSource
) : CloudRepository {

    override suspend fun getPhotos(): AppResult<List<Photo>>{
        return cloudRemoteDataSource.getPhotos()
    }

    override suspend fun saveBitmapToCache(key: Int, bitmap: Bitmap){
        return cloudLocalDataSource.saveBitmapToCache(key, bitmap)
    }

    override suspend fun retrieveBitmapFromCache(key: Int): Bitmap?{
        return cloudLocalDataSource.retrieveBitmapFromCache(key)
    }
}
