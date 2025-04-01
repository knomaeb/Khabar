package com.example.khabar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.khabar.data.local.DatabaseService
import com.example.khabar.data.local.entity.BookmarkHeadline
import com.example.khabar.data.model.HeadlineQuery
import com.example.khabar.data.paging.HeadlinesPagingSource
import com.example.khabar.data.remote.NetworkService
import com.example.khabar.data.remote.model.Headline
import com.example.khabar.utils.AppConstants.PAGE_SIZE
import com.example.khabar.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val dispatcherProvider: DispatcherProvider
) {
    fun getHeadlinesByCountry(countryCode: String): Flow<PagingData<Headline>>{
        return getHeadlines(HeadlineQuery.ByCountry(countryCode))
    }

    fun getHeadlinesByCategory(category: String): Flow<PagingData<Headline>>{
        return getHeadlines(HeadlineQuery.ByCategory(category = category))
    }

    fun search(query: String): Flow<PagingData<Headline>> {
        return getHeadlines(HeadlineQuery.BySearch(query = query))
    }

    private fun getHeadlines(headlineQuery: HeadlineQuery): Flow<PagingData<Headline>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                HeadlinesPagingSource(
                    networkService = networkService,
                    databaseService = databaseService,
                    dispatcherProvider = dispatcherProvider,
                    query = headlineQuery
                )
            }
        ).flow
    }

    suspend fun getCachedHeadlines(): Flow<List<Headline>> {
        return withContext(dispatcherProvider.io) { databaseService.getCachedHeadlines() }
    }

    suspend fun bookmarkHeadline(headline: Headline) {
        withContext(dispatcherProvider.io) { databaseService.bookmarkHeadline(headline = headline) }
    }

    suspend fun removeFromBookmarkedHeadlines(headline: BookmarkHeadline) {
        withContext(dispatcherProvider.io) { databaseService.removeFromBookmarkedHeadlines(headline = headline) }
    }

    suspend fun getBookmarkedHeadlines(): Flow<List<BookmarkHeadline>> {
        return withContext(dispatcherProvider.io) { databaseService.getBookmarkedHeadlines() }
    }
}