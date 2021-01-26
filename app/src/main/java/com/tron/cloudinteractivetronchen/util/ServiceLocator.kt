package com.tron.cloudinteractivetronchen.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.tron.cloudinteractivetronchen.data.CloudDataSource
import com.tron.cloudinteractivetronchen.data.CloudRepository
import com.tron.cloudinteractivetronchen.data.DefaultCloudRepository
import com.tron.cloudinteractivetronchen.data.local.CloudLocalDataSource
import com.tron.cloudinteractivetronchen.data.remote.CloudRemoteDataSource


object ServiceLocator {

    @Volatile
    var cloudRepository: CloudRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): CloudRepository {
        synchronized(this) {
            return cloudRepository
                ?: cloudRepository
                ?: createCloudRepository(context)
        }
    }

    private fun createCloudRepository(context: Context): CloudRepository {
        return DefaultCloudRepository(
            CloudRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): CloudDataSource {
        return CloudLocalDataSource(context)
    }
}