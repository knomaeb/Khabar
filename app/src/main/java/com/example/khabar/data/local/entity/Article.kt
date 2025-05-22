package com.example.khabar.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_articles")
data class Article(
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
    val source: Source,
    @ColumnInfo(name = "content")
    val content: String
)

fun Article.articleToSavedArticleEntity(): SavedArticleEntity {
    return SavedArticleEntity(
        id = id,
        source = source.sourceToSavedSourceEntity(),
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        content = content
    )
}

fun List<Article>.filterArticles(): List<Article> {
    return this.filterNot { article ->
        article.title.isEmpty() || article.description.isEmpty() || article.imageUrl.isEmpty()
    }
}
