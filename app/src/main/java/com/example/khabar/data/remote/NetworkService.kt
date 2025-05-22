package com.example.khabar.data.remote

import com.example.khabar.core.utils.AppConstants.DEFAULT_COUNTRY
import com.example.khabar.core.utils.AppConstants.DEFAULT_LANGUAGE
import com.example.khabar.core.utils.AppConstants.DEFAULT_PAGE_NUM
import com.example.khabar.core.utils.AppConstants.DEFAULT_QUERY_PAGE_SIZE
import com.example.khabar.core.utils.AppConstants.DEFAULT_SOURCE
import com.example.khabar.data.model.News
import com.example.khabar.data.model.Sources
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") countryCode: String = DEFAULT_COUNTRY,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("top-headlines")
    suspend fun getNewsByLang(
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("top-headlines")
    suspend fun getNewsBySource(
        @Query("sources") sources: String = DEFAULT_SOURCE,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("top-headlines")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News

    @GET("sources")
    suspend fun getSources(): Sources
}