package com.example.khabar.data.local

import com.example.khabar.data.local.entity.BookmarkHeadline
import com.example.khabar.data.remote.model.Headline
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getCachedHeadlines(): Flow<List<Headline>>

    fun deleteAllAndInsertAllToCache(headlines: List<Headline>)

    fun cacheAll(headlines: List<Headline>)

    fun getBookmarkedHeadlines(): Flow<List<BookmarkHeadline>>

    fun bookmarkHeadline(headline: Headline)

    fun removeFromBookmarkedHeadlines(headline: BookmarkHeadline)
}