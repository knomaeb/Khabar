package com.example.khabar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.khabar.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM cached_articles")
    fun getAll(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(articles: List<Article>)

    @Query("DELETE FROM cached_articles")
    fun removeAll()

    @Transaction
    fun deleteAllAndInsertAll(articles: List<Article>) {
        removeAll()
        return addAll(articles)
    }
}