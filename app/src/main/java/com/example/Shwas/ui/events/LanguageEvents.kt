package com.example.Shwas.ui.events

sealed class LanguageEvents: Events.UIEvent() {
    data class UpdateSelectedLanguage(val selectedLanguageCode: String, val changeLocale: Boolean = false) : LanguageEvents()
}