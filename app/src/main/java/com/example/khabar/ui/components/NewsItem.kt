package com.example.khabar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsItem(
    imageUrl: String,
    author: String,
    title: String,
    publishedAt: String,
    modifier: Modifier,
    onClick: () -> Unit,
){
    Card(
        modifier = modifier,
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
            BookmarkButton()
        }

        Spacer(Modifier.height(10.dp))

        HorizontalDivider(thickness = 1.dp)
    }
}

@Composable
fun AuthorText(text: String){
    if (text.isNotEmpty()){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = text,
        )
    }
}

@Composable
fun TitleText(text: String){
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
fun PublishedTextAt(text: String){
    if (text.isNotEmpty()){
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text = text,
        )
    }
}

@Composable
fun BookmarkButton(){
    Icon(
        Icons.Default.FavoriteBorder,
        contentDescription = null
    )
}

@Preview
@Composable
fun NewsItemPreview(){
    NewsItem(
        imageUrl = "https://d.newswek.com/en/full/2616963/florida-wisconsin-elections-what-know.png",
        author = "Sunil Singh Rana",
        title = "Florida Wisconsin elections: What do you know? What happened next? After some time forget it.",
        publishedAt = "5 hours ago",
        modifier = Modifier,
        onClick = {},
    )
}