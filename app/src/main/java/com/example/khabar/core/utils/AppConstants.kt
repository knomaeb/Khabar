package com.example.khabar.core.utils

import com.example.khabar.BuildConfig


object AppConstants {
    const val API_KEY = BuildConfig.API_KEY

    const val SEARCH_NEWS_TIME_DELAY = 500L
    const val DEFAULT_QUERY_PAGE_SIZE = 20
    const val DEFAULT_PAGE_NUM = 1
    const val DEFAULT_COUNTRY = "in"
    const val DEFAULT_LANGUAGE = "en"
    const val DEFAULT_SOURCE = "abc-news"

    const val DEFAULT_CATEGORY = "general"

    const val PAGE_SIZE = 15

    const val APP_NAME = "Khabar"

    const val DIALOG_ERROR_HEADER = "Error Occurred!"

    const val DIALOG_NETWORK_ERROR = "No internet connection. Try again later"
}