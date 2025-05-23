package com.example.khabar.domain.repository

import androidx.paging.PagingData
import com.example.khabar.data.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(
        category: String?,
        country: String?,
        searchQuery: String?,
    ): Flow<PagingData<News>>

    fun getNewsByCategory(
        category: String,
        pageSize: Int,
        page: Int
    ): Flow<PagingData<News>>
}