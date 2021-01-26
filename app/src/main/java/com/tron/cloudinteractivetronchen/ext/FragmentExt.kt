package com.tron.cloudinteractivetronchen.ext

import androidx.fragment.app.Fragment
import com.tron.cloudinteractivetronchen.CloudApplication
import com.tron.cloudinteractivetronchen.factory.ViewModelFactory


fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as CloudApplication).cloudRepository
    return ViewModelFactory(repository)
}