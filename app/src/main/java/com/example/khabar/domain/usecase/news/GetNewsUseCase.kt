package com.example.khabar.domain.usecase.news

import com.example.khabar.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(
        country: String?,
        category: String?,
    ) = repository.getNews(
        country = country,
        category = category,
        searchQuery = null
    )
}