package com.tron.cloudinteractivetronchen.data

import android.graphics.Bitmap

interface CloudRepository {
    suspend fun getPhotos(): AppResult<List<Photo>>
    suspend fun saveBitmapToCache(key: Int, bitmap: Bitmap)
    suspend fun retrieveBitmapFromCache(key: Int): Bitmap?
}
