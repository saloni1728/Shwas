package com.example.Shwas.ui.viewModels

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.example.Shwas.MainActivity
import com.example.Shwas.domain.model.AppUseCasesModel
import com.example.Shwas.domain.model.onError
import com.example.Shwas.domain.model.onSuccess
import com.example.Shwas.ui.events.EventHandlerViewModel
import com.example.Shwas.ui.events.LanguageEvents
import com.example.Shwas.ui.uiStates.LanguageUIState
import com.example.Shwas.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val appUseCasesModel: AppUseCasesModel
) : EventHandlerViewModel<LanguageEvents>() {

    private val _screenState = MutableStateFlow(LanguageUIState.empty)
    val screenState = _screenState.asStateFlow()

    init {
        fetchAllLanguages()
    }

    override fun handleEvent(event: LanguageEvents) {
        when (event) {
            is LanguageEvents.UpdateSelectedLanguage -> {
                if (event.changeLocale) {
                    changeLocale(event.selectedLanguageCode)
                }
                _screenState.update { it.copy(selectedLanguageCode = event.selectedLanguageCode) }
            }
            else -> {}
        }
    }

    private fun fetchAllLanguages() {
        viewModelScope.launch {
            appUseCasesModel.languageUseCase.fetchLanguages()
                .flowOn(Dispatchers.IO)
                .collectLatest { result ->
                    result.onSuccess { languages ->
                        _screenState.update { it.copy(languageList = languages) }
                    }.onError { failure ->
                        System.out.println(failure)
                    }
            }

        }
    }

    private fun changeLocale(language: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentLanguage = language ?: appUseCasesModel.languageUseCase.getSavedLanguagePreference()
            val context = appUseCasesModel.languageUseCase.setLocale(currentLanguage)
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra(Constants.PREFERRED_LANGUAGE, currentLanguage)
            context.startActivity(intent)
        }
    }
}