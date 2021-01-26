package com.tron.cloudinteractivetronchen.data

import androidx.lifecycle.LiveData



interface CloudDataSource {

    suspend fun getPhotos(): AppResult<List<Photo>>
}
