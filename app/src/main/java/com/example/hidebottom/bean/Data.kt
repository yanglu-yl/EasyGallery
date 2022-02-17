package com.example.hidebottom.bean

data class Data(
    var _id: String,
    var author: String,
    var category: String,
    var createdAt: String,
    var desc: String,
    var images: List<String>,
    var likeCounts: Int,
    var publishedAt: String,
    var stars: Int,
    var title: String,
    var type: String,
    var url: String,
    var views: Int
)