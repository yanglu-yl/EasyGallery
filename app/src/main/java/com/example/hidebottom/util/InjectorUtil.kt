package com.example.hidebottom.util

import com.example.hidebottom.PhotoModeFactory
import com.example.hidebottom.http.PhotoNetwork
import com.example.hidebottom.http.PhotoRepository


object InjectorUtil {

    private fun getHomeRepository() = PhotoRepository.getInstance(PhotoNetwork.getInstance())

    fun getPhotoModel() = PhotoModeFactory(getHomeRepository())

}