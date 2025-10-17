package com.example.composeshinobicima.appcore.di

import com.example.composeshinobicima.appcore.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideMovieApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)


}