package com.example.khabar.data.local

import androidx.room.Entity
import com.example.khabar.domain.model.News

@Entity(tableName = "favourite_news", primaryKeys = ["title"])
data class NewsEntity(
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String,
    val source: String,
    val publishedAt: String,
    val author: String,
    val url: String
) {
    companion object {
        fun News.toEntity(): NewsEntity = NewsEntity(
            title = title,
            description = description,
            content = content,
            imageUrl = imageUrl,
            source = source,
            publishedAt = publishedAt,
            author = author,
            url = url
        )

        fun NewsEntity.toNews(): News = News(
            title = title,
            description = description,
            content = content,
            imageUrl = imageUrl,
            source = source,
            publishedAt = publishedAt,
            author = author,
            url = url
        )
    }
}