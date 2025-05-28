package com.example.Shwas.ui.viewModels

import androidx.lifecycle.viewModelScope
import com.example.Shwas.data.dataPersistence.PreferencesDataStore
import com.example.Shwas.ui.events.AppHostEvents
import com.example.Shwas.ui.events.EventHandlerViewModel
import com.example.Shwas.ui.screens.ScreenType
import com.example.Shwas.ui.uiStates.AppHostUIState
import com.example.Shwas.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppHostViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
): EventHandlerViewModel<AppHostEvents>() {

    private val _screenState = MutableStateFlow(AppHostUIState.default)
    val screenState = _screenState.asStateFlow()

    override fun handleEvent(event: AppHostEvents) {
        when (event) {
            is AppHostEvents.SetStartDestination -> {
                viewModelScope.launch {
                    val startDestination = if (preferencesDataStore.getValue(Constants.PREFERRED_LANGUAGE, "").isEmpty()) {
                        ScreenType.LanguageScreen
                    } else ScreenType.HomeScreen
                    _screenState.update { it.copy(startDestination = startDestination) }
                }
            }
        }
    }

}