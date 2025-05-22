package com.example.khabar.data.model

import com.example.khabar.data.local.entity.Article
import com.example.khabar.data.local.entity.Source
import com.google.gson.annotations.SerializedName

data class ApiArticle(

    @SerializedName("author")
    val author: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("source")
    val source: Source

)

fun ApiArticle.apiArticleToArticle(): Article {
    return Article(
        source = source,
        author = author,
        title = title,
        description = description,
        url = url,
        publishedAt = publishedAt,
        content = content,
        imageUrl = imageUrl
    )
}

fun List<ApiArticle>.apiArticleListToArticleList(): List<Article> {
    val list = mutableListOf<Article>()
    forEach { apiArticle ->
        list.add(apiArticle.apiArticleToArticle())
    }
    return list
}

fun List<ApiSource>.apiSourceListToSourceList(): List<Source> {
    val list = mutableListOf<Source>()
    forEach { apiSource ->
        list.add(apiSource.apiSourceToSource())
    }
    return list
}