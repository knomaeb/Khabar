package com.example.khabar.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.khabar.data.remote.NetworkService
import com.example.khabar.data.remote.dto.NewsResponseDto.Companion.toNews
import com.example.khabar.domain.model.News
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val newsApi: NetworkService,
    private val country: String?,
    private val category: String?,
    private val searchQuery: String?
) : PagingSource<Int, News>() {
    override fun getRefreshKey(state: PagingState<Int, News>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val response =
                newsApi.fetchNews(
                    country = country,
                    category = category,
                    searchQuery = searchQuery,
                    pageSize = params.loadSize,
                    page = nextPage
                )

            LoadResult.Page(
                data = response.articles.map { it.toNews() },
                prevKey = if (nextPage == FIRST_PAGE_INDEX) null else nextPage - 1,
                nextKey = if (response.articles.isEmpty()) null else nextPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val FIRST_PAGE_INDEX = 0
    }
}