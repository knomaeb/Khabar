package com.example.khabar.data.remote.model

import com.example.khabar.data.local.entity.BookmarkHeadline
import com.example.khabar.data.local.entity.CacheHeadline
import com.example.khabar.data.model.HeadlineContract
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Headline(
    @SerialName("author")
    override val author: String = "",
    @SerialName("title")
    override val title: String = "",
    @SerialName("description")
    override val description: String = "",
    @SerialName("url")
    override val url: String = "",
    @SerialName("urlToImage")
    override val imageUrl: String = "",
    @SerialName("publishedAt")
    override val publishedAt: String = "",
    @SerialName("source")
    override val source: Source,
    @SerialName("content")
    override val content: String
): HeadlineContract

fun Headline.toBookmarkHeadline(): BookmarkHeadline {
    return BookmarkHeadline(
        url = url,
        author = author,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        source = source.toSourceEntity(),
        content = content
    )
}

fun Headline.toCacheHeadline(): CacheHeadline {
    return CacheHeadline(
        url = url,
        author = author,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        source = source.toSourceEntity(),
        content = content
    )
}
