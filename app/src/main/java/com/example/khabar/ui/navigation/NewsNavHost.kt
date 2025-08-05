package com.example.khabar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.khabar.ui.common.NewsUiModel
import com.example.khabar.ui.common.NewsUiModelParameterType
import com.example.khabar.ui.details.NewsDetailsScreen
import com.example.khabar.ui.favourite.FavouritesScreen
import com.example.khabar.ui.home.components.HomeScreen
import com.example.khabar.ui.search.SearchNewsScreen
import kotlin.reflect.typeOf

@Composable
fun NewsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.NewsList
    ) {
        composable<Destinations.NewsList> {
            HomeScreen(
                navController = navController
            )
        }
        composable<Destinations.NewsDetails>(
            typeMap = mapOf(typeOf<NewsUiModel>() to NewsUiModelParameterType)
        ) { backStackEntry ->
            val news = backStackEntry.toRoute<Destinations.NewsDetails>().news
            NewsDetailsScreen(
                news = news,
                navController = navController
            )
        }
        composable<Destinations.Favourites> {
            FavouritesScreen(
                navController = navController
            )
        }
        composable<Destinations.SearchNews> {
            SearchNewsScreen(
                navController = navController
            )
        }
    }
}