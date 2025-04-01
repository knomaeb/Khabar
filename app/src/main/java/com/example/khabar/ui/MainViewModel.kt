package com.example.khabar.ui

import androidx.lifecycle.ViewModel
import com.example.khabar.data.model.Country
import com.example.khabar.data.remote.model.HeadlinesParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // network status
    private val _isNetworkConnected = MutableStateFlow(false)
    val isNetworkConnected: StateFlow<Boolean> = _isNetworkConnected

    fun updateNetworkStatus(isNetworkConnected: Boolean) {
        _isNetworkConnected.update { isNetworkConnected }
    }

    // Headline request params
    private val _headlinesParams = MutableStateFlow(HeadlinesParams())

    val headlinesParams: StateFlow<HeadlinesParams> = _headlinesParams

    fun updateSelectedCountry(country: Country) {
        _headlinesParams.update { it.copy(selectedCountry = country) }
    }

    fun updateSelectedCategory(category: String) {
        _headlinesParams.update { it.copy(selectedCategory = category) }
    }
}