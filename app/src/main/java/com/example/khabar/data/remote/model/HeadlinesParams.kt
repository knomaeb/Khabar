package com.example.khabar.data.remote.model

import com.example.khabar.data.model.Country
import com.example.khabar.utils.AppConstants.DEFAULT_COUNTRY_CODE
import com.example.khabar.utils.AppConstants.DEFAULT_COUNTRY_FLAG
import com.example.khabar.utils.AppConstants.DEFAULT_COUNTRY_NAME
import com.example.khabar.utils.AppConstants.DEFAULT_LANGUAGE_CODE
import com.example.khabar.utils.AppConstants.DEFAULT_SOURCE
import com.example.khabar.utils.AppConstants.DEFAULT_CATEGORY

data class HeadlinesParams(
    val selectedCountry: Country = Country(
        code = DEFAULT_COUNTRY_CODE,
        name = DEFAULT_COUNTRY_NAME,
        flag = DEFAULT_COUNTRY_FLAG
    ),
    val selectedLanguageCode: String = DEFAULT_LANGUAGE_CODE,
    val selectedCategory: String = DEFAULT_CATEGORY,
    val selectedSourceId : String = DEFAULT_SOURCE
)
