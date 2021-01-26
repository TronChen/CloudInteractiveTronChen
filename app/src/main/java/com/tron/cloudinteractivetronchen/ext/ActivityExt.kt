package com.tron.cloudinteractivetronchen.ext

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.tron.cloudinteractivetronchen.CloudApplication
import com.tron.cloudinteractivetronchen.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as CloudApplication).cloudRepository
    return ViewModelFactory(repository)
}

fun Activity?.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}