package com.example.reachmobi.di

import android.content.Context
import com.example.reachmobi.data.repository.NewsRepository
import com.example.reachmobi.data.repository.NewsRepositoryImpl
import com.example.reachmobi.network.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://newsapi.org/v2/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRetrofit() : NewsService {
        val instance = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return instance.create(NewsService::class.java)
    }

    @Provides
    fun provideWeatherRepository(service: NewsService): NewsRepository {
        return NewsRepositoryImpl(service)
    }
}