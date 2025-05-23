package com.example.khabar.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.khabar.core.dispatcher.DispatcherProvider
import com.example.khabar.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {


}