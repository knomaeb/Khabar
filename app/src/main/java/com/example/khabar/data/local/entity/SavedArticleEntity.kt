package com.example.khabar.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "saved_headlines",
    indices = [Index(value = ["url", "publishedAt"], unique = true)]
)
data class SavedArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
    @Embedded
    val source: SavedSourceEntity,
    @ColumnInfo(name = "content")
    val content: String
)

fun SavedArticleEntity.savedArticleEntityToArticle(): Article {
    return Article(
        id = id,
        source = source.savedSourceEntityToSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        content = content
    )
}
