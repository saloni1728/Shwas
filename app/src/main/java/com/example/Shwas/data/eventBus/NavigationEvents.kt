package com.example.Shwas.data.eventBus

import com.example.Shwas.ui.screens.ScreenType

sealed class NavigationEvents {
    data class NavigateTo(val route: ScreenType, val params: Map<String, Any> = emptyMap()) : NavigationEvents()
    data class NavigateBack(val count: Int = 1) : NavigationEvents()
    object NavigateUp : NavigationEvents()
}