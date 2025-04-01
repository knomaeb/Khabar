package com.example.khabar.data.remote

import com.example.khabar.data.remote.model.HeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getHeadlinesByCountry(
        @Query("country") countryCode: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): HeadlinesResponse

    @GET("top-headlines")
    suspend fun getHeadlinesByCategory(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ) : HeadlinesResponse

    @GET("everything")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): HeadlinesResponse
}