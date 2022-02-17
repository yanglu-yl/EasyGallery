package com.example.hidebottom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hidebottom.http.PhotoRepository
import com.example.hidebottom.viewModel.PhotoViewModel

class PhotoModeFactory(private val hr: PhotoRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(hr) as T
    }

}