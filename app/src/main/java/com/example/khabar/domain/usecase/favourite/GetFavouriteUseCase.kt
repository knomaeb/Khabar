package com.example.khabar.domain.usecase.favourite

import com.example.khabar.domain.repository.FavouriteRepository
import javax.inject.Inject

class GetFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke() = repository.getFavorites()
}