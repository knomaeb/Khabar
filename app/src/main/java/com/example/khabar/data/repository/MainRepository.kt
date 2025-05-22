package com.example.khabar.data.repository

import com.example.khabar.core.dispatcher.DispatcherProvider
import com.example.khabar.core.utils.AppConstants.DEFAULT_PAGE_NUM
import com.example.khabar.core.utils.CountryHelper
import com.example.khabar.core.utils.LanguageHelper
import com.example.khabar.data.local.DatabaseService
import com.example.khabar.data.local.entity.Article
import com.example.khabar.data.local.entity.Source
import com.example.khabar.data.model.Country
import com.example.khabar.data.model.Language
import com.example.khabar.data.model.apiArticleListToArticleList
import com.example.khabar.data.model.apiSourceListToSourceList
import com.example.khabar.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val dispatcherProvider: DispatcherProvider
) {

    suspend fun getNews(pageNumber: Int = DEFAULT_PAGE_NUM): List<Article> {
        val articles = networkService.getNews(
            pageNum = pageNumber
        ).articles.apiArticleListToArticleList()
        return if (pageNumber == DEFAULT_PAGE_NUM) {
            databaseService.deleteAllAndInsertAll(articles)
            databaseService.getAllArticles().first()
        } else {
            articles
        }
    }

    suspend fun getNewsFromDb(): List<Article> {
        return databaseService.getAllArticles().first()
    }

    fun getNewsByCountry(
        countryCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            networkService.getNews(
                countryCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    fun getNewsByCategory(
        category: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            networkService.getNewsByCategory(
                category,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    fun getNewsByLanguage(
        languageCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            networkService.getNewsByLang(
                languageCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    fun getNewsBySource(
        sourceCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            networkService.getNewsBySource(
                sourceCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    fun searchNews(
        searchQuery: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> =
        flow {
            emit(
                networkService.searchNews(
                    searchQuery,
                    pageNumber
                ).articles.apiArticleListToArticleList()
            )
        }

    suspend fun upsert(article: Article) {
        databaseService.upsert(article)
    }

    suspend fun getSavedNews(): Flow<List<Article>> = databaseService.getSavedArticles()

    suspend fun deleteArticle(article: Article) = databaseService.deleteArticle(article)

    fun getSources(): Flow<List<Source>> = flow {
        emit(
            networkService.getSources().sources.apiSourceListToSourceList()
        )
    }

    fun getCountries(): Flow<List<Country>> = flow {
        emit(CountryHelper.countries)
    }

    fun getLanguages(): Flow<List<Language>> = flow {
        emit(LanguageHelper.languages)
    }

}