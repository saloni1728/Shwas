package com.example.Shwas.ui.uiStates

import androidx.compose.runtime.Immutable
import com.example.Shwas.ui.screens.ScreenType

@Immutable
data class AppHostUIState(
    val startDestination: ScreenType? = null
) {
    companion object {
        val default = AppHostUIState()
    }
}