package com.tron.cloudinteractivetronchen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tron.cloudinteractivetronchen.data.CloudRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val cloudRepository: CloudRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
