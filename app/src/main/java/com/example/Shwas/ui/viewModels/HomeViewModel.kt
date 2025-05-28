package com.example.Shwas.ui.viewModels

import androidx.lifecycle.viewModelScope
import com.example.Shwas.data.eventBus.NavigationEventBus
import com.example.Shwas.data.eventBus.NavigationEvents
import com.example.Shwas.domain.model.AppUseCasesModel
import com.example.Shwas.domain.model.onError
import com.example.Shwas.domain.model.onSuccess
import com.example.Shwas.ui.events.EventHandlerViewModel
import com.example.Shwas.ui.events.HomeEvents
import com.example.Shwas.ui.screens.ScreenType
import com.example.Shwas.ui.uiStates.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appUseCasesModel: AppUseCasesModel,
    private val navigationEventBus: NavigationEventBus
): EventHandlerViewModel<HomeEvents>() {

    private val _screenState = MutableStateFlow(HomeUIState.empty)
    val screenState = _screenState.asStateFlow()

    init {
        fetchInitData()
    }

    override fun handleEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.UpdateSearchQuery -> {
                _screenState.update { it.copy(searchQuery = event.query) }
            }
            is HomeEvents.UpdateExpandedCardId -> {
                markExpanded(event.id)
            }
            is HomeEvents.MarkFavorite -> {
                markAsFavorite(event.id)
            }
            is HomeEvents.NavigateToMeditationScreen -> {
                viewModelScope.launch {
                    navigationEventBus.navigate(NavigationEvents.NavigateTo(
                        ScreenType.MeditationScreen(
                            meditationId = event.id,
                            title = event.id,
                            cardColor = event.cardColor,
                            textColor = event.textColor,
                            accentColor = event.accentColor
                        )
                    ))
                }
            }
            else -> {}
        }
    }

    private fun markAsFavorite(id: String) {
        viewModelScope.launch {
            val open = if(screenState.value.favoriteIds.contains(id)) 0 else 1
            appUseCasesModel.breathingExercisesUseCase.setFavoriteExercise(id, open)
                .flowOn(Dispatchers.IO)
                .collectLatest { result ->
                    result.onSuccess { success ->
                        val favoriteIds = screenState.value.favoriteIds.toMutableSet()
                        if (open == 0) {
                            favoriteIds.remove(id)
                        } else favoriteIds.add(id)
                        _screenState.update { it.copy(
                            favoriteIds = favoriteIds.toSet(),
                            breathingExercise = success
                        ) }
                    }.onError { failure ->
                        System.out.println(failure)
                    }
                }
        }
    }

    private fun markExpanded(id: String) {
        viewModelScope.launch {
            val open = if(screenState.value.expandedCardIds.contains(id)) 0 else 1
            appUseCasesModel.breathingExercisesUseCase.setExpandedExercise(id, open)
                .flowOn(Dispatchers.IO)
                .collectLatest { result ->
                    result.onSuccess { success ->
                        val expandedIds = screenState.value.expandedCardIds.toMutableSet()
                        if (open == 0) {
                            expandedIds.remove(id)
                        } else expandedIds.add(id)
                        _screenState.update { it.copy(
                            expandedCardIds = expandedIds.toSet(),
                            breathingExercise = success
                        ) }
                    }.onError { failure ->
                        System.out.println(failure)
                    }
                }
        }
    }

    private fun fetchInitData() {
        viewModelScope.launch {
            val breathingData = async(Dispatchers.IO) {
                appUseCasesModel.breathingExercisesUseCase.getBreathingExercises()
                    .collectLatest { result ->
                        result.onSuccess { exercises ->
                            _screenState.update { it.copy(breathingExercise = exercises) }
                        }.onError { failure ->
                            System.out.println(failure)
                        }
                    }
            }
            val currentLanguage = async {
                appUseCasesModel.languageUseCase.getSavedLanguagePreference()
                    .let { language ->
                        _screenState.update { state -> state.copy(currentLanguage = language) }
                    }
            }
            breathingData.await()
            currentLanguage.await()
        }
    }
}