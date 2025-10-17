package com.example.composeshinobicima

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeshinobicima.appcore.navigation.ScreenResources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomNavViewModel : ViewModel() {
    private val _currentRoute = MutableStateFlow<ScreenResources>(ScreenResources.HomeScreenRoute)
    val currentRoute: StateFlow<ScreenResources> = _currentRoute

    fun onRouteSelected(route: ScreenResources) {
        viewModelScope.launch {
            _currentRoute.emit(route)
        }
    }
}