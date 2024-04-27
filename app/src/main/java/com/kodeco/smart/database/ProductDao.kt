package com.kodeco.smart.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kodeco.smart.models.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(vararg product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM `products`")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM `products` WHERE isFavorite = 1")
    suspend fun getFavoriteProducts(): List<Product>

    @Query("DELETE FROM `products`")
    suspend fun deleteAllProducts()
}