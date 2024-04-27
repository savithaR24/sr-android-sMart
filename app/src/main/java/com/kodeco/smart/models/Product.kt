package com.kodeco.smart.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,
    val productTitle: String,
    val productImageUrl: String,
    val productCategory: String,
    val productDescription: String,
    val productPrice: Double,
    val isFavorite: Boolean = false,
) : Parcelable
