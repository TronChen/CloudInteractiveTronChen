package com.tron.cloudinteractivetronchen.networks

import android.graphics.Bitmap
import com.google.gson.GsonBuilder
import com.tron.cloudinteractivetronchen.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


private const val BASE_URL = "https://via.placeholder.com"

var gson = GsonBuilder()
    .setLenient()
    .create()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface PicAPIService {

    @GET("150")
    suspend fun getBitmaps(@Query("id")id : String): Bitmap

}


object PicApi {
    val retrofitService : PicAPIService by lazy { retrofit.create(PicAPIService::class.java) }
}