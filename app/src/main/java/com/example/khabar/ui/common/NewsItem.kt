package com.example.khabar.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.khabar.core.utils.getFirstAuthor
import com.example.khabar.core.utils.toRelativeTime
import com.example.khabar.ui.theme.fontFamily

@Composable
fun NewsItem(
    news: NewsUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp)
                .clickable {onClick()},
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NewsImage(
                imageUrl = news.imageUrl,
                contentDescription = news.title,
                modifier =
                    Modifier
                        .size(width = 120.dp, height = 120.dp)
                        .fillMaxWidth(.32f)
            )

            Column(
                modifier =
                    Modifier
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = news.source,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = fontFamily,
                    textDecoration = TextDecoration.Underline
                )

                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = fontFamily
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = getFirstAuthor(news.author),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = fontFamily
                    )

                    Text(
                        text = news.publishedAt.toRelativeTime(),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    HorizontalDivider(
        modifier = Modifier
            .padding(top = 8.dp),
        color = MaterialTheme.colorScheme.outline
    )
}

@Preview(showBackground = true)
@Composable
private fun NewsItemPrev() {
    NewsItem(
        news = NewsUiModel(
            title = "This is a news title of this demo news item for the preview of this item.",
            imageUrl = "",
            source = "Google News",
            description = "This is description of the news by project dome of khabar app",
            content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            publishedAt = "14th August, 2025",
            author = "Mahatma Gandhi",
            url = "https:''",
        ),
        onClick = {}
    )
}