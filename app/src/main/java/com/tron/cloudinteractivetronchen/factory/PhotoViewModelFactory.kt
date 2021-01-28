package com.tron.cloudinteractivetronchen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tron.cloudinteractivetronchen.data.CloudRepository
import com.tron.cloudinteractivetronchen.data.Photo
import com.tron.cloudinteractivetronchen.third.ThirdViewModel

@Suppress("UNCHECKED_CAST")
class PhotoViewModelFactory(
    private val repository: CloudRepository,
    private val photo: Photo?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            return photo?.let {
                ThirdViewModel(
                    repository,
                    it
                )
            } as T
        }


        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}