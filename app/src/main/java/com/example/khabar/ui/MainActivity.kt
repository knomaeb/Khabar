package com.example.khabar.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.khabar.ui.navigation.BottomNavigation
import com.example.khabar.ui.navigation.Destinations
import com.example.khabar.ui.navigation.NewsNavHost
import com.example.khabar.ui.theme.KhabarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            KhabarTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                    ?: Destinations.NewsList::class.qualifiedName.orEmpty()
                val showBottomNavigation =
                    currentRoute in BottomNavigation.entries.map { it.route::class.qualifiedName }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        if (showBottomNavigation) {
                            Column {
                                HorizontalDivider(thickness = .2.dp)
                                BottomAppBar(
                                    modifier = Modifier.testTag("bottom_navigation"),
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    tonalElevation = 0.dp,
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    BottomNavigation.entries
                                        .forEach { navigationItem ->
                                            val isSelected by remember(currentRoute) {
                                                derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                                            }

                                            NavigationBarItem(
                                                modifier = Modifier
                                                    .testTag(navigationItem.name),
                                                selected = isSelected,
                                                label = {
                                                    Text(
                                                        text = navigationItem.label,
                                                        style = MaterialTheme.typography.labelSmall
                                                    )
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = if (isSelected) navigationItem.selectedIcon else navigationItem.unselectedIcon,
                                                        contentDescription = navigationItem.label,
                                                    )
                                                },
                                                onClick = {
                                                    navController.navigate(navigationItem.route)
                                                },
                                                colors = NavigationBarItemDefaults.colors(
                                                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                        elevation = NavigationBarDefaults.Elevation,
                                                    ),
                                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                                                    unselectedTextColor = MaterialTheme.colorScheme.onSurface
                                                )
                                            )
                                        }
                                }
                            }
                        }
                    }
                ) {
                    NewsNavHost(
                        modifier = Modifier.padding(it),
                        navController = navController
                    )
                }


            }
        }
    }


}