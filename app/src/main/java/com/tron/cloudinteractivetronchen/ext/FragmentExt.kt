package com.tron.cloudinteractivetronchen.ext

import androidx.fragment.app.Fragment
import com.tron.cloudinteractivetronchen.CloudApplication
import com.tron.cloudinteractivetronchen.data.Photo
import com.tron.cloudinteractivetronchen.factory.PhotoViewModelFactory
import com.tron.cloudinteractivetronchen.factory.ViewModelFactory


fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as CloudApplication).cloudRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(photo: Photo?): PhotoViewModelFactory {
    val repository = (requireContext().applicationContext as CloudApplication).cloudRepository
    return PhotoViewModelFactory(repository, photo)
}