package com.example.khabar.core.utils

import com.example.khabar.data.model.Language

object LanguageHelper {
    val languages = listOf(
        Language("ar", "Arabic"),
        Language("de", "German"),
        Language("en", "English"),
        Language("es", "Spanish"),
        Language("fr", "French"),
        Language("he", "Hebrew"),
        Language("it", "Italian"),
        Language("nl", "Dutch"),
        Language("no", "Norwegian"),
        Language("pt", "Portuguese"),
        Language("ru", "Russian"),
        Language("sv", "Swedish"),
        Language("zh", "Chinese")
    )

    fun getAllLanguages(): List<Language> {
        return languages
    }
}