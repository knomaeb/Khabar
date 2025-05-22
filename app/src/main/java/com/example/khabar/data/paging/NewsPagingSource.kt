package com.example.khabar.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.khabar.core.dispatcher.DispatcherProvider
import com.example.khabar.core.network.NetworkConnected
import com.example.khabar.core.network.NoInternetException
import com.example.khabar.core.utils.AppConstants
import com.example.khabar.data.local.entity.Article
import com.example.khabar.data.repository.MainRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val newsRepository: MainRepository,
    private val networkConnected: NetworkConnected,
    private val dispatcherProvider: DispatcherProvider
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        lateinit var loadResult: LoadResult<Int, Article>

        withContext(dispatcherProvider.io) {
            kotlin.runCatching {
                if (!networkConnected.inNetworkConnected()) {
                    if (page == AppConstants.DEFAULT_PAGE_NUM) {
                        val articles = newsRepository.getNewsFromDb()
                        loadResult = LoadResult.Page(
                            data = articles,
                            prevKey = page.minus(1),
                            nextKey = if (articles.isEmpty()) null else page.plus(1)
                        )
                    } else {
                        throw NoInternetException()
                    }
                } else {
                    val articles = newsRepository.getNews(page)
                    loadResult = LoadResult.Page(
                        data = articles,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (articles.isEmpty()) null else page.plus(1)
                    )
                }
            }.onFailure {
                loadResult = LoadResult.Error(it)
            }
        }
        return loadResult
    }
}