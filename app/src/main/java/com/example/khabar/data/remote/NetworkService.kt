package com.example.khabar.data.remote

import com.example.khabar.BuildConfig
import com.example.khabar.core.utils.AppConstants.DEFAULT_CATEGORY_CODE
import com.example.khabar.core.utils.AppConstants.DEFAULT_COUNTRY_CODE
import com.example.khabar.core.utils.AppConstants.DEFAULT_PAGE_NUM
import com.example.khabar.core.utils.AppConstants.DEFAULT_QUERY_PAGE_SIZE
import com.example.khabar.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("country") country: String? = DEFAULT_COUNTRY_CODE,
        @Query("category") category: String? = DEFAULT_CATEGORY_CODE,
        @Query("q") searchQuery: String?,
        @Query("page") page: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): NewsResponseDto
}