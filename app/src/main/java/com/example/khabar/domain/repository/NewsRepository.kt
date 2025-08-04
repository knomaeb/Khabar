package com.example.khabar.domain.repository

import androidx.paging.PagingData
import com.example.khabar.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(
        category: String?,
        country: String?,
        searchQuery: String?,
    ): Flow<PagingData<News>>
}