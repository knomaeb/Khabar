package com.example.khabar.data.local

import com.example.khabar.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    //Saving News
    suspend fun upsert(article: Article)

    suspend fun getSavedArticles(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article)

    //Caching News
    fun getAllArticles(): Flow<List<Article>>

    fun deleteAllAndInsertAll(articles: List<Article>)

}