package com.example.hidebottom.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hit(
    var collections: Int,
    var comments: Int,
    var downloads: Int,
    var id: Int,
    var imageHeight: Int,
    var imageSize: Int,
    var imageWidth: Int,
    var largeImageURL: String,
    var likes: Int,
    var pageURL: String,
    var previewHeight: Int,
    var previewURL: String,
    var previewWidth: Int,
    var tags: String,
    var type: String,
    var user: String,
    var userImageURL: String,
    var user_id: Int,
    var views: Int,
    var webformatHeight: Int,
    var webformatURL: String,
    var webformatWidth: Int
): Parcelable