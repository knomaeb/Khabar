package com.example.khabar.core.utils

object ValidationUtil {
    fun checkIfValidArgNews(str: String?): Boolean {
        return !(str.isNullOrEmpty() || str == "{country}" || str == "{language}" || str == "{source}")
    }
}