package com.example.khabar.di.module

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.khabar.core.dispatcher.DefaultDispatcherProvider
import com.example.khabar.core.dispatcher.DispatcherProvider
import com.example.khabar.core.logger.DefaultLogger
import com.example.khabar.core.logger.Logger
import com.example.khabar.core.network.NetworkConnected
import com.example.khabar.core.network.NetworkHelper
import com.example.khabar.core.utils.AppConstants
import com.example.khabar.core.utils.AppConstants.API_KEY
import com.example.khabar.data.local.AppDatabase
import com.example.khabar.data.local.AppDatabaseService
import com.example.khabar.data.local.DatabaseService
import com.example.khabar.data.local.entity.Article
import com.example.khabar.data.paging.NewsPagingSource
import com.example.khabar.data.remote.AuthInterceptor
import com.example.khabar.data.remote.NetworkService
import com.example.khabar.di.ApiKey
import com.example.khabar.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideNetworkConnected(@ApplicationContext context: Context): NetworkConnected {
        return NetworkConnected(context)
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideCustomTabIntent(): CustomTabsIntent {
        return CustomTabsIntent.Builder().build()
    }

    @BaseUrl
    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @ApiKey
    @Singleton
    @Provides
    fun provideApiKey(): String = API_KEY

    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        @ApiKey apiKey: String,
        gsonFactory: GsonConverterFactory
    ): NetworkService {

        val authInterceptor = AuthInterceptor(apiKey = apiKey)

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = DefaultLogger()

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun providePager(
        newsPagingSource: NewsPagingSource
    ): Pager<Int, Article> {
        return Pager(
            config = PagingConfig(
                AppConstants.DEFAULT_QUERY_PAGE_SIZE
            )
        ) {
            newsPagingSource
        }
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ): NetworkHelper {
        return NetworkConnected(context)
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "khabar_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: AppDatabase): DatabaseService {
        return AppDatabaseService(appDatabase)
    }
}