package com.example.khabar.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

sealed class Route(val route: String){
    data object HomeScreen: Route("home_screen")
    data object SearchScreen : Route("search_screen")
    data object NewsDetails: Route("news_details")
    data object BookmarksScreen : Route("bookmark_screen")
    data object SettingsScreen: Route("settings_screen")
}

@Composable
fun AppNavHost(
  modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.HomeScreen.route
    ){
        composable(route = Route.HomeScreen.route){}
    }
}