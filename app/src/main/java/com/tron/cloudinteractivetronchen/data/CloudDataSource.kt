package com.tron.cloudinteractivetronchen.data

import android.graphics.Bitmap
import androidx.lifecycle.LiveData



interface CloudDataSource {

    suspend fun getPhotos(): AppResult<List<Photo>>

    suspend fun saveBitmapToCache(key: Int, bitmap: Bitmap)

    suspend fun retrieveBitmapFromCache(key: Int): Bitmap?
}
