package com.example.khabar.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    data object TopNews : Destinations()

    @Serializable
    data object SearchNews : Destinations()

    @Serializable
    data object SavedNews : Destinations()
}

val bottomBarScreens = listOf(
    Destinations.TopNews,
    Destinations.SearchNews,
    Destinations.SavedNews
)