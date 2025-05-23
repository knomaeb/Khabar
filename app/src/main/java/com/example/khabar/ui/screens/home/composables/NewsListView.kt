package com.example.khabar.ui.screens.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.khabar.core.utils.AppConstants.DIALOG_ERROR_HEADER
import com.example.khabar.core.utils.AppConstants.DIALOG_NETWORK_ERROR
import com.example.khabar.data.model.HeadlineContract
import com.example.khabar.ui.base.ClickHandler
import com.example.khabar.ui.base.HeadlineHandler
import com.example.khabar.ui.base.RetryHandler
import com.example.khabar.ui.common.MaxFillNoDataFound
import com.example.khabar.ui.common.MaxFillProgressLoading
import com.example.khabar.ui.common.ShowErrorDialog

@Composable
fun <T> LoadPaginatedHeadlines(
    headlines: LazyPagingItems<T>,
    isNetworkConnected: Boolean,
    onHeadlineClicked: HeadlineHandler<T>,
    onBookmarkClicked: HeadlineHandler<T>,
    onRetryClicked: RetryHandler,
    listState: LazyListState = rememberLazyListState(),
) where T : HeadlineContract {

    PaginatedHeadlineList(
        headlines = headlines,
        onHeadlineClicked = onHeadlineClicked,
        onBookmarkClicked = onBookmarkClicked,
        listState = listState
    )

    headlines.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> {
                HandleHeadlineLoading(isNetworkConnected = isNetworkConnected)
            }

            is LoadState.NotLoading -> {
                if (headlines.itemCount == 0) {
                    HandleHeadlineLoading(isNetworkConnected = isNetworkConnected)
                }
            }

            is LoadState.Error -> {
                val error = headlines.loadState.refresh as LoadState.Error
                Box(modifier = Modifier.fillMaxSize()) {
                    ShowErrorDialog(
                        header = DIALOG_ERROR_HEADER, message = if (isNetworkConnected) {
                            error.error.localizedMessage!!
                        } else {
                            DIALOG_NETWORK_ERROR
                        }
                    ) { onRetryClicked() }
                }
            }
        }
    }

}

@Composable
fun <T> PaginatedHeadlineList(
    headlines: LazyPagingItems<T>,
    onHeadlineClicked: HeadlineHandler<T>,
    onBookmarkClicked: HeadlineHandler<T>,
    listState: LazyListState = rememberLazyListState(),
) where T : HeadlineContract {
    LazyColumn(state = listState) {
        items(
            headlines.itemCount,
            key = { index -> "${headlines[index]?.source}_${headlines[index]?.url}_${headlines[index]?.publishedAt}" }) { index ->
            val headline = headlines[index]!!
            NewsListView(
                imageUrl = headline.imageUrl,
                author = headline.author,
                title = headline.title,
                publishedAt = headline.publishedAt,
                onClick = { onHeadlineClicked(headline) },
                onBookmarkClicked = { onBookmarkClicked(headline) }
            )
        }
    }
}

@Composable
fun HandleHeadlineLoading(isNetworkConnected: Boolean) {
    if (isNetworkConnected) {
        MaxFillProgressLoading()
    } else {
        MaxFillNoDataFound()
    }
}

@Composable
fun NewsListView(
    imageUrl: String,
    author: String,
    title: String,
    publishedAt: String,
    onClick: ClickHandler,
    onBookmarkClicked: ClickHandler
) {
    Card(
        modifier = Modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Spacer(Modifier.height(8.dp))

        NewsImage(
            imageUrl = imageUrl
        )

        Spacer(Modifier.height(8.dp))

        AuthorText(text = author)

        Spacer(Modifier.height(8.dp))

        TitleText(text = title)

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PublishedTextAt(text = publishedAt)
            Box(
                modifier = Modifier
                    .clickable { onBookmarkClicked() }
            ) {
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = null
                )
            }

        }

        Spacer(Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp)
    }
}

@Composable
fun AuthorText(text: String) {
    if (text.isNotEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = text,
        )
    }
}

@Composable
fun TitleText(text: String) {
    if (text.isNotEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            text = text,
            fontSize = 20.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PublishedTextAt(text: String) {
    if (text.isNotEmpty()) {
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text = text,
        )
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    NewsListView(
        imageUrl = "https://d.newswek.com/en/full/2616963/florida-wisconsin-elections-what-know.png",
        author = "Sunil Singh Rana",
        title = "Florida Wisconsin elections: What do you know? What happened next? After some time forget it.",
        publishedAt = "5 hours ago",
        onClick = {},
        onBookmarkClicked = {},
    )
}