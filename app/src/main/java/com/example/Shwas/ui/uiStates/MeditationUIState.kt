package com.example.Shwas.ui.uiStates

import android.speech.tts.Voice
import androidx.compose.runtime.Immutable
import androidx.core.text.util.LocalePreferences.HourCycle
import com.example.Shwas.domain.model.TimedInstruction

@Immutable
data class MeditationUIState(
    val musicVolume: Float = 0.5f,
    val ttsVolume: Float = 0.5f,
    val breathingExercisesSteps: List<TimedInstruction>? = null,
    val currentLanguage: String = "",
    val selectedVoice: Voice? = null,
    val currentStep: Int = 0,
    val currentInstruction: TimedInstruction? = null,
    val numberOfCycles: Int = 1
) {
    companion object {
        val empty = MeditationUIState()
    }
}