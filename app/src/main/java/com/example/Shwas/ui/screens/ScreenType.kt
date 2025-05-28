package com.example.Shwas.ui.screens

import kotlinx.serialization.Serializable

sealed class ScreenType {
    @Serializable
    object LanguageScreen: ScreenType()

    @Serializable
    object HomeScreen: ScreenType()

    @Serializable
    data class MeditationScreen(
        val meditationId: String,
        val cardColor: Int,
        val textColor: Int,
        val accentColor: Int,
        val title: String
    ) : ScreenType()

}