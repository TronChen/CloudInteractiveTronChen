package com.tron.cloudinteractivetronchen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tron.cloudinteractivetronchen.data.CloudRepository
import com.tron.cloudinteractivetronchen.first.FirstViewModel
import com.tron.cloudinteractivetronchen.second.SecondViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val cloudRepository: CloudRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(FirstViewModel::class.java) ->
                    FirstViewModel(cloudRepository)

                isAssignableFrom(SecondViewModel::class.java) ->
                    SecondViewModel(cloudRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
