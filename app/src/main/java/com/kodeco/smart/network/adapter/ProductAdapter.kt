package com.kodeco.smart.network.adapter

import com.kodeco.smart.models.Product
import com.kodeco.smart.network.dto.ProductDto
import com.kodeco.smart.ui.screens.list.ProductListViewModel
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WrappedProductList
class ProductAdapter {
    @WrappedProductList
    @FromJson
    fun fromJson(productDtoList: List<ProductDto>): List<Product> =
        productDtoList.map { productDto ->
            Product(
                id = productDto.id,
                productTitle = productDto.title,
                productImageUrl = productDto.image,
                productCategory = productDto.category,
                productDescription = productDto.description,
                productPrice = productDto.price,
            )
        }

    @ToJson
    fun toJson(@WrappedProductList productList: List<Product>): List<ProductDto> =
        productList.map { product ->
            ProductDto(
                id = product.id,
                title = product.productTitle,
                image = product.productImageUrl,
                price = product.productPrice,
                category = product.productCategory,
                description = product.productDescription,
                )
        }
}