package com.example.khabar.di.module

import android.content.Context
import androidx.room.Room
import com.example.khabar.data.local.AppDatabase
import com.example.khabar.data.local.AppDatabaseService
import com.example.khabar.data.local.DatabaseService
import com.example.khabar.data.remote.AuthInterceptor
import com.example.khabar.data.remote.NetworkService
import com.example.khabar.utils.AppConstants.API_KEY
import com.example.khabar.utils.DefaultDispatcherProvider
import com.example.khabar.utils.DispatcherProvider
import com.example.khabar.utils.network.NetworkConnected
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
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

    @Singleton
    @Provides
    fun provideNetworkService(@BaseUrl baseUrl: String, @ApiKey apiKey: String): NetworkService {

        val authInterceptor = AuthInterceptor(apiKey = apiKey)

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        val contentType = "application/json".toMediaType()

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
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

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()
}