package com.example.khabar.data.model

sealed class HeadlineQuery {
    data class ByCountry(val countryCode : String) : HeadlineQuery()
    data class ByCategory(val category: String) : HeadlineQuery()
    data class BySearch(val query: String) : HeadlineQuery()
}