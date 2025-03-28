package com.example.khabar.di.module

import android.content.Context
import com.example.khabar.utils.AppConstants.API_KEY
import com.example.khabar.utils.network.NetworkConnected
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideNetworkConnected(@ApplicationContext context: Context): NetworkConnected{
        return NetworkConnected(context)
    }

    @BaseUrl
    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return "https://newsapi.org/v2/"
    }

    @ApiKey
    @Singleton
    @Provides
    fun provideApiKey(): String {
        return API_KEY
    }
}