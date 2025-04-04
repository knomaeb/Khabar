package com.example.khabar.data.model

interface HeadlineContract {
    val author: String
    val content: String
    val title: String
    val description: String
    val url: String
    val imageUrl: String
    val publishedAt: String
    val source: SourceContract
}