package com.example.khabar.domain.usecase.favourite

import com.example.khabar.domain.model.News
import com.example.khabar.domain.repository.FavouriteRepository
import javax.inject.Inject

class RemoveFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(news: News) = repository.removeFavorite(news)
}