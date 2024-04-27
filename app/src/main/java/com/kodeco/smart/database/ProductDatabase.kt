package com.kodeco.smart.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodeco.smart.models.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false,
)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_database"
        ).build()
    }
}