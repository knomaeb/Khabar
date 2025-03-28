package com.example.khabar.presentation.base

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>

    data class Error(val message: String) : UiState<Nothing>

    data object Loading : UiState<Nothing>
}