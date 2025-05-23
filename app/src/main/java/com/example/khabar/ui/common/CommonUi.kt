package com.example.khabar.ui.common

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.DialogProperties
import com.example.khabar.R
import com.example.khabar.ui.base.ClickHandler
import com.example.khabar.ui.base.RetryHandler

@Composable
fun PrimaryButton(modifier: Modifier, text: String, onClick: ClickHandler) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(size = 12.dp),
        onClick = onClick
    ) { Text(text = text, fontSize = 14.sp) }
}

@Composable
fun BackButton(onClick: ClickHandler) {
    IconButton(
        modifier = Modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.Back_Button),
            tint = colorScheme.primary
        )
    }
}

@Composable
fun IconButton(
    modifier: Modifier,
    drawablePainter: Painter,
    contentDescription: String,
    onClick: ClickHandler
) {
    Icon(
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() },
        painter = drawablePainter,
        contentDescription = contentDescription
    )
}

@Composable
fun MaxFillProgressLoading() {
    Box(modifier = Modifier.fillMaxSize()) { ProgressLoading(modifier = Modifier.align(Alignment.Center)) }
}

@Composable
fun ProgressLoading(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(64.dp),
        color = colorScheme.secondary,
        trackColor = colorScheme.surfaceVariant,
    )
}

@Composable
fun ShowErrorDialog(
    header: String,
    message: String,
    onRetryClick: RetryHandler,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AlertDialog(
            onDismissRequest = { /* No action to make it non-cancellable */ },
            title = { Text(text = header) },
            text = { Text(text = message) },
            confirmButton = { Button(onClick = onRetryClick) { Text(text = stringResource(R.string.RETRY)) } },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        )
    }
}

@Composable
fun NoNetworkStatusBar(onViewOfflineHeadlinesClick: ClickHandler) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(3.dp),
            text = stringResource(R.string.NO_INTERNET_CONNECTION),
            fontSize = 14.sp
        )

        HorizontalDivider()

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .clickable { onViewOfflineHeadlinesClick() },
            text = stringResource(R.string.VIEW_NEWS_OFFLINE),
            fontSize = 14.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun MaxFillNoDataFound() {
    Box(modifier = Modifier.fillMaxSize()) { NoDataFound(modifier = Modifier.align(Alignment.Center)) }
}

@Composable
fun NoDataFound(modifier: Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.NO_DATA_FOUND), fontSize = 24.sp)
    }
}

@Composable
fun WebViewPage(
    url: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(modifier = Modifier, text = "Primary Button") {}
}

@Preview(showBackground = true)
@Composable
fun BackButtonPreview() {
    BackButton(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun MaxFillProgressLoadingPreview() {
    MaxFillProgressLoading()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProgressLoadingPreview() {
    ProgressLoading(modifier = Modifier)
}

@Preview
@Composable
fun ApiRetryAlertPreview() {
    ShowErrorDialog(
        header = "Error title",
        message = "Error message",
        onRetryClick = { /* Retry logic */ })
}

@Preview(showBackground = true)
@Composable
fun NoNetworkStatusBarPreview() {
    NoNetworkStatusBar {}
}

@Preview(showBackground = true)
@Composable
fun MaxFillNoDataFoundPreview() {
    MaxFillNoDataFound()
}

@Preview(showBackground = true)
@Composable
fun NoDataFoundPreview() {
    NoDataFound(modifier = Modifier)
}