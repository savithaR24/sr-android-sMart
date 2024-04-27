package com.kodeco.smart.di

import android.content.Context
import com.kodeco.smart.api.ProductAPIService
import com.kodeco.smart.database.ProductDatabase
import com.kodeco.smart.network.adapter.ProductAdapter
import com.kodeco.smart.prefs.ProductPrefs
import com.kodeco.smart.prefs.ProductPrefsImpl
import com.kodeco.smart.repositories.ProductRepository
import com.kodeco.smart.repositories.ProductRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://fakestoreapi.com"

@Module
@InstallIn(SingletonComponent::class)
class ProductInfoSingletonModule {
    @Provides
    @Singleton
    fun providesProductAPIService(): ProductAPIService {
        val moshi = Moshi.Builder()
            .add(ProductAdapter())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(ProductAPIService::class.java)
    }

    @Provides
    @Singleton
    fun providesProductDatabase(@ApplicationContext applicationContext: Context): ProductDatabase {
        return ProductDatabase.buildDatabase(applicationContext)
    }

    @Provides
    @Singleton
    fun providesProductPrefs(@ApplicationContext applicationContext: Context): ProductPrefs {
        return ProductPrefsImpl(applicationContext)
    }

    @Provides
    @Singleton
    fun providesProductRepository(
        service: ProductAPIService,
        database: ProductDatabase,
        prefs: ProductPrefs,
    ): ProductRepository =
        ProductRepositoryImpl(service, database.productDao(), prefs)
}