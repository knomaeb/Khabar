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
    override val title: String = "",
    override val description: String = "",
    override val url: String = "",
    override val imageUrl: String = "",
    override val publishedAt: String = "",
    override val source: Source
): HeadlineContract

fun Headline.toBookmarkHeadline(): BookmarkHeadline {
    return BookmarkHeadline(
        url = url,
        author = author,
        title = title,
        description = description,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        source = source.toSourceEntity()
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
        source = source.toSourceEntity()
    )
}
