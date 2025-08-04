package com.example.khabar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.khabar.core.utils.AppConstants.DEFAULT_QUERY_PAGE_SIZE
import com.example.khabar.data.paging.NewsPagingSource
import com.example.khabar.data.remote.NetworkService
import com.example.khabar.domain.model.News
import com.example.khabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NetworkService,
) : NewsRepository {

    override fun getNews(
        category: String?,
        country: String?,
        searchQuery: String?
    ): Flow<PagingData<News>> = Pager(
        config =
            PagingConfig(
                pageSize = DEFAULT_QUERY_PAGE_SIZE,
                enablePlaceholders = false
            ),
        pagingSourceFactory = {
            NewsPagingSource(
                newsApi = newsApi,
                country = country,
                category = category,
                searchQuery = searchQuery
            )
        }
    ).flow

}