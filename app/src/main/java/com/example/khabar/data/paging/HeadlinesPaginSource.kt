package com.example.khabar.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.khabar.data.local.DatabaseService
import com.example.khabar.data.model.HeadlineQuery
import com.example.khabar.data.remote.NetworkService
import com.example.khabar.data.remote.model.Headline
import com.example.khabar.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class HeadlinesPagingSource(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val dispatcherProvider: DispatcherProvider,
    private val query: HeadlineQuery
) : PagingSource<Int, Headline>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Headline> {

        return try {

            val position = params.key ?: 1

            val response = when (query) {

                is HeadlineQuery.ByCountry -> {
                    networkService.getHeadlinesByCountry(
                        countryCode = query.countryCode,
                        page = position,
                        pageSize = params.loadSize
                    )
                }


                is HeadlineQuery.BySearch -> {
                    networkService.search(
                        query = query.query,
                        page = position,
                        pageSize = params.loadSize
                    )
                }

                is HeadlineQuery.ByCategory -> {
                    networkService.getHeadlinesByCategory(
                        category = query.category,
                        page = position,
                        pageSize = params.loadSize
                    )
                }
            }

            val filteredHeadlines =
                response.headlines.filter { it.title.uppercase() != "[REMOVED]" }

            withContext(dispatcherProvider.io) {
                if (query is HeadlineQuery.ByCountry) {
                    if (position == 1) {
                        databaseService.deleteAllAndInsertAllToCache(headlines = filteredHeadlines)
                    } else {
                        databaseService.cacheAll(headlines = filteredHeadlines)
                    }
                }
            }

            LoadResult.Page(
                data = filteredHeadlines,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.headlines.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Headline>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
