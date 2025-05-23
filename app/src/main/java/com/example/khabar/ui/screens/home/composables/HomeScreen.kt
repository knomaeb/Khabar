package com.example.khabar.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.khabar.R
import com.example.khabar.core.utils.AppConstants.APP_NAME
import com.example.khabar.ui.base.ClickHandler
import com.example.khabar.ui.common.IconButton
import com.example.khabar.ui.navigation.Route
import com.example.khabar.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {

    val uiState by


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                navController = navController,
                scrollBehavior = scrollBehavior
            )
        },

        ) { paddingValues ->


    }


}

@Composable
fun NoInternetScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.NetworkCheck,
            contentDescription = "No Internet"
        )
        Text(text = "No Internet Connection")
    }
}

@Preview(showBackground = true)
@Composable
fun NoInternetScreenPreview() {
    NoInternetScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            TitleAppName()
        },
        navigationIcon = {
            SearchButton {
                navController.navigate(Route.SearchScreen.route)
            }
        },
        actions = {
            ProfileButton()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchButton(onClickHandler: ClickHandler) {
    IconButton(
        modifier = Modifier,
        drawablePainter = painterResource(id = R.drawable.ic_search_icon),
        contentDescription = "Search button",
    ) { onClickHandler() }
}

@Composable
private fun TitleAppName() {
    Text(
        text = APP_NAME,
        modifier = Modifier,
        fontFamily = FontFamily.Serif,
    )
}

@Composable
private fun ProfileButton() {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TopAppBarPrev() {
    TopAppBar(
        navController = NavController(LocalContext.current),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    )
}
