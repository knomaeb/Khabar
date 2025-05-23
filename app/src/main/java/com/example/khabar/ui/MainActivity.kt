package com.example.khabar.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.khabar.core.network.NetworkConnected
import com.example.khabar.ui.navigation.AppNavHost
import com.example.khabar.ui.theme.KhabarTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkConnected: NetworkConnected

    @Inject
    lateinit var customTabsIntent: CustomTabsIntent

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        observerNetworkChanges()
        setContent {
            KhabarTheme {
                AppNavHost(
                    mainViewModel = mainViewModel,
                    customTabsIntent = customTabsIntent,
                )
            }
        }
    }

    private fun observerNetworkChanges() {
        networkConnected.observe(this@MainActivity) {
            mainViewModel.updateNetworkStatus(it)
        }
    }

}