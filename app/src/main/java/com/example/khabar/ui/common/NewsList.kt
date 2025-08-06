package com.example.khabar.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun NewsList(
    onClickNews: (NewsUiModel) -> Unit,
    newsPaging: LazyPagingItems<NewsUiModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .background(Color.Transparent),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(newsPaging.itemCount) {
            val news = newsPaging[it]
            if (news != null) {
                NewsItem(
                    news = news,
                    onClick = {
                        onClickNews(news)
                    }
                )
            }
        }
        newsPaging.loadState.let { loadState ->
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingStateComponent()
                        }
                    }
                }

                loadState.refresh is LoadState.NotLoading && newsPaging.itemCount < 1 -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            EmptyComponent(
                                message = "No News Available"
                            )
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorStateComponent(
                                message = "Something went wrong. Error: ${error.error.message}"
                            )
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier =
                                    Modifier
                                        .size(16.dp)
                                        .align(Alignment.Center),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }
        }
    }
}