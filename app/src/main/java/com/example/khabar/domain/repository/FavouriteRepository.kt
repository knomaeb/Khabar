package com.example.khabar.domain.repository

import com.example.khabar.domain.model.News
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun addFavorite(news: News)

    suspend fun removeFavorite(news: News)

    fun getFavorites(): Flow<List<News>>

    fun isFavorite(news: News): Flow<Boolean>
}