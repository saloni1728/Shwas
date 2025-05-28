package com.example.Shwas.ui.uiStates

import androidx.compose.runtime.Immutable
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity

@Immutable
data class HomeUIState(
    val searchQuery: String = "",
    val breathingExercise: List<BreathingExerciseEntity> = emptyList(),
    val currentLanguage: String = "",
    val expandedCardIds: Set<String> = emptySet(),
    val favoriteIds: Set<String> = emptySet(),
) {
    companion object {
        val empty = HomeUIState()
    }
}