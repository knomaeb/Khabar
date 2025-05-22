package com.example.khabar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.khabar.data.local.entity.SavedArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedArticleDao {

    @Query("SELECT * FROM saved_headlines")
    suspend fun getSavedArticles(): Flow<List<SavedArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // replace the existing one
    suspend fun add(article: SavedArticleEntity)

    @Delete
    suspend fun remove(article: SavedArticleEntity)
}