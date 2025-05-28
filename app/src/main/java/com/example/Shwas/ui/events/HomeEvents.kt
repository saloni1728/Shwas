package com.example.Shwas.ui.events

sealed class HomeEvents: Events.UIEvent() {
    data class UpdateSearchQuery(val query: String) : HomeEvents()
    data class UpdateExpandedCardId(val id: String) : HomeEvents()
    data class MarkFavorite(val id: String) : HomeEvents()
    data class NavigateToMeditationScreen(
        val id: String,
        val cardColor: Int,
        val textColor: Int,
        val accentColor: Int
    ) : HomeEvents()
}