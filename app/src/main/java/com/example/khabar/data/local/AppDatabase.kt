package com.example.khabar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.khabar.data.local.dao.ArticleDao
import com.example.khabar.data.local.dao.SavedArticleDao
import com.example.khabar.data.local.entity.Article
import com.example.khabar.data.local.entity.SavedArticleEntity

@Database(
    entities = [SavedArticleEntity::class, Article::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSavedArticleDao(): SavedArticleDao
    abstract fun getArticleDao(): ArticleDao
}