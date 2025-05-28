package com.example.Shwas.domain.useCases

import android.content.Context
import com.example.Shwas.domain.repositories.ILanguageRepo

class LanguageUseCase(private val languageRepo: ILanguageRepo) {
    suspend fun getSavedLanguagePreference(): String {
        return languageRepo.getLanguage()
    }

    suspend fun setLocale(language: String): Context {
        return languageRepo.setLocale(language)
    }

    fun fetchLanguages() = languageRepo.fetchLanguages()
}