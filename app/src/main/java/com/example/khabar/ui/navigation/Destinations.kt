package com.example.khabar.ui.navigation

import com.example.khabar.ui.common.NewsUiModel
import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    data object NewsList

    @Serializable
    data object SearchNews

    @Serializable
    data class NewsDetails(val news: NewsUiModel)

    @Serializable
    data object Favourites

    @Serializable
    object Settings

    @Serializable
    object Language
}