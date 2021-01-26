package com.tron.cloudinteractivetronchen.data

interface CloudRepository {
    suspend fun getPhotos(): AppResult<List<Photo>>
}
