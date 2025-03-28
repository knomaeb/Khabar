package com.example.khabar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.khabar.data.local.dao.BookmarkHeadlinesDao
import com.example.khabar.data.local.dao.CacheHeadlinesDao
import com.example.khabar.data.local.entity.BookmarkHeadline
import com.example.khabar.data.local.entity.CacheHeadline

@Database(
    entities = [ BookmarkHeadline::class, CacheHeadline::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkHeadlinesDao(): BookmarkHeadlinesDao
    abstract fun cacheHeadlinesDao(): CacheHeadlinesDao
}