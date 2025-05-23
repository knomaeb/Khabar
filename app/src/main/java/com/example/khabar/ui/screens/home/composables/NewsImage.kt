package com.example.khabar.ui.screens.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.khabar.R

@Composable
fun NewsImage(
    imageUrl: String,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(8.dp))
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "headline_image",
            modifier = Modifier.matchParentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsImagePreview(){
    NewsImage(
        imageUrl = "https://d.newswek.com/en/full/2616963/florida-wisconsin-elections-what-know.png",
    )
}