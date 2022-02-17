package com.example.hidebottom.bean

data class GrilBean(
    var `data`: List<Data>,
    var page: Int,
    var page_count: Int,
    var status: Int,
    var total_counts: Int
)