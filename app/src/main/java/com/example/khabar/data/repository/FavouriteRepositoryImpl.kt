package com.example.khabar.data.repository

import com.example.khabar.data.local.NewsDao
import com.example.khabar.data.local.NewsEntity.Companion.toEntity
import com.example.khabar.data.local.NewsEntity.Companion.toNews
import com.example.khabar.domain.model.News
import com.example.khabar.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : FavouriteRepository {
    override suspend fun addFavorite(news: News) {
        newsDao.insert(news.toEntity())
    }

    override suspend fun removeFavorite(news: News) {
        newsDao.delete(news.toEntity())
    }

    override fun getFavorites(): Flow<List<News>> = newsDao.getAll().map { newsList ->
        newsList.map {
            it.toNews()
        }
    }

    override fun isFavorite(news: News): Flow<Boolean> = newsDao.isFavorite(news.title)
}