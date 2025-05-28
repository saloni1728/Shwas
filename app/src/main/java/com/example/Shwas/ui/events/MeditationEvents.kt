package com.example.Shwas.ui.events

import android.content.Context

sealed class MeditationEvents: Events.UIEvent() {
    data class UpdateMusicVolume(val volume: Float) : MeditationEvents()
    data class UpdateTTSVolume(val volume: Float) : MeditationEvents()
    object NavigateBack : MeditationEvents()
    data class FetchInitData(val id: String, val context: Context) : MeditationEvents()
    data class UpdateCurrentStep(val step: Int) : MeditationEvents()
}