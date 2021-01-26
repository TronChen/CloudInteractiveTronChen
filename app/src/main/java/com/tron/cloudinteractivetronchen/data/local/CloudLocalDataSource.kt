package com.tron.cloudinteractivetronchen.data.local

import android.content.Context
import com.tron.cloudinteractivetronchen.data.AppResult
import com.tron.cloudinteractivetronchen.data.CloudDataSource
import com.tron.cloudinteractivetronchen.data.Photo


/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Concrete implementation of a Stylish source as a db.
 */
class CloudLocalDataSource(val context: Context) : CloudDataSource {
    override suspend fun getPhotos(): AppResult<List<Photo>> {
        TODO("Not yet implemented")
    }

}
