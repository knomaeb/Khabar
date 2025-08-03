package com.example.khabar.di

import com.example.khabar.data.repository.FavouriteRepositoryImpl
import com.example.khabar.data.repository.NewsRepositoryImpl
import com.example.khabar.domain.repository.FavouriteRepository
import com.example.khabar.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindFavoriteRepository(repository: FavouriteRepositoryImpl): FavouriteRepository
}