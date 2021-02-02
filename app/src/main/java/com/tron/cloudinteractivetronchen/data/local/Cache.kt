package com.tron.cloudinteractivetronchen.data.local

import android.graphics.Bitmap
import android.util.LruCache

class Cache private constructor() {

    private object HOLDER {
        val INSTANCE = Cache()
    }

    companion object {
        val instance: Cache by lazy { HOLDER.INSTANCE }
    }
    val lru: LruCache<Any, Any> = LruCache(1024)

    fun saveBitmapToCache(key: String, bitmap: Bitmap) {

        try {
            instance.lru.put(key, bitmap)
        } catch (e: Exception) {
        }

    }

    fun retrieveBitmapFromCache(key: String): Bitmap? {

        try {
            return instance.lru.get(key) as Bitmap?
        } catch (e: Exception) {
        }

        return null
    }

}