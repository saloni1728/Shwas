package com.example.Shwas.ui.uiStates

import androidx.compose.runtime.Immutable
import com.example.Shwas.data.dataPersistence.RoomDb.entity.LanguageEntity

@Immutable
data class LanguageUIState(
    val selectedLanguageCode: String = "",
    val languageList: List<LanguageEntity> = emptyList()
) {
    companion object {
        val empty = LanguageUIState()
    }
}