package com.tron.cloudinteractivetronchen.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.tron.cloudinteractivetronchen.CloudApplication

object Utils {

    fun isInternetConnected(): Boolean {
        val cm = CloudApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int): String {
        return CloudApplication.instance.getString(resourceId)
    }

}