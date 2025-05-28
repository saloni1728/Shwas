package com.example.Shwas.ui.viewModels

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity
import com.example.Shwas.data.eventBus.NavigationEventBus
import com.example.Shwas.data.eventBus.NavigationEvents
import com.example.Shwas.domain.model.AppUseCasesModel
import com.example.Shwas.domain.model.onError
import com.example.Shwas.domain.model.onSuccess
import com.example.Shwas.ui.events.EventHandlerViewModel
import com.example.Shwas.ui.events.MeditationEvents
import com.example.Shwas.ui.uiStates.MeditationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MeditationViewModel @Inject constructor(
    private val navigationEventBus: NavigationEventBus,
    private val appUseCasesModel: AppUseCasesModel
): EventHandlerViewModel<MeditationEvents>() {

    private val _screenState = MutableStateFlow(MeditationUIState.empty)
    val screenState = _screenState.asStateFlow()

    override fun handleEvent(event: MeditationEvents) {
        when (event) {
            is MeditationEvents.UpdateMusicVolume -> {
                _screenState.update { it.copy(musicVolume = event.volume) }
            }
            is MeditationEvents.UpdateTTSVolume -> {
                _screenState.update { it.copy(ttsVolume = event.volume) }
            }
            is MeditationEvents.NavigateBack -> {
                viewModelScope.launch {
                    navigationEventBus.navigate(NavigationEvents.NavigateUp)
                }
            }
            is MeditationEvents.FetchInitData -> {
                fetchInitData(event.id, event.context)
            }
            is MeditationEvents.UpdateCurrentStep -> {
                _screenState.update { it.copy(
                    currentStep = event.step,
                    currentInstruction = screenState.value.breathingExercisesSteps?.getOrNull(event.step)
                ) }
            }
        }
    }

    private fun fetchInitData(id: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            appUseCasesModel.languageUseCase.getSavedLanguagePreference()
                .let { language ->
                    appUseCasesModel.breathingExercisesUseCase.getBreathingExercisesStep(id)
                        .collect { result ->
                            result.onSuccess { steps ->
                                selectTTSVoice(context, language)
                                val instructions = steps.let { breathingSteps ->
                                    BreathingStepsEntity.combineInstructions(
                                        language = language,
                                        prepPrompt = breathingSteps.prepPrompt,
                                        beginPrompt = breathingSteps.beginPrompt,
                                        continuePrompt = breathingSteps.continuePrompt,
                                        endPrompt = breathingSteps.endPrompt,
                                        cycles = breathingSteps.numberOfCycle
                                    )
                                }
                                _screenState.update {
                                    it.copy(
                                        breathingExercisesSteps = instructions,
                                        currentLanguage = language,
                                        numberOfCycles = steps.numberOfCycle,
                                        currentInstruction = instructions.getOrNull(0)
                                    )
                                }
                            }.onError { failure ->
                                System.out.println("Error fetching breathing steps: ${failure}")
                            }
                        }
                }
        }
    }

    private fun selectTTSVoice(
        context: Context,
        languageCode: String,
        countryCode: String? = "IN",
        preferredGender: String? = "female"
    ) {
        var textToSpeech: TextToSpeech? = null
        val tts = TextToSpeech(context, { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech!!.language = Locale(languageCode, countryCode ?: "")
                System.out.println("TextToSpeech initialized with language: ${textToSpeech!!.language}")
            } else {
                System.out.println("Failed to initialize TextToSpeech")
            }
        })
        textToSpeech = tts
        System.out.println("saloni : $textToSpeech ${textToSpeech.voices}")
        val targetLocale = if (countryCode != null)
            Locale(languageCode, countryCode)
        else
            Locale(languageCode)

        val voices = textToSpeech.voices ?: return
        voices.forEach {
            System.out.println("Voice: ${it.name}, Locale: ${it.locale}, Features: ${it.features}")
        }

        val matchingVoices = voices.filter {
            it.locale.language == targetLocale.language &&
                    (countryCode == null || it.locale.country == targetLocale.country)
        }

        if (matchingVoices.isEmpty()) return

        val selected = preferredGender?.let { gender ->
            matchingVoices.firstOrNull { voice ->
                val name = voice.name.lowercase()
                gender.lowercase() in name || gender.lowercase() in (voice.features ?: emptySet())
            }
        } ?: matchingVoices.first()

        _screenState.update { it.copy(selectedVoice = selected) }
    }
}