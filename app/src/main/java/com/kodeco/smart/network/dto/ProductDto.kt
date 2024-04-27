package com.kodeco.smart.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    val id: Int,
    val title: String,
    val image: String,
    val price: Double,
    val description: String,
    val category: String,

    )
