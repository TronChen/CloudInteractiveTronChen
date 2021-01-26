package com.tron.cloudinteractivetronchen.networks

import com.tron.cloudinteractivetronchen.BuildConfig
import com.tron.cloudinteractivetronchen.data.Photo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface CloudApiService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object CloudApi {
    val retrofitService : CloudApiService by lazy { retrofit.create(CloudApiService::class.java) }
}