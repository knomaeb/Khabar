package com.example.khabar.domain.usecase.favourite

import com.example.khabar.domain.model.News
import com.example.khabar.domain.repository.FavouriteRepository
import javax.inject.Inject

class IsFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(news: News) = repository.isFavorite(news)
}