package com.example.Shwas.domain.model

import com.example.Shwas.domain.useCases.BreathingExercisesUseCase
import com.example.Shwas.domain.useCases.LanguageUseCase

data class AppUseCasesModel(
    val languageUseCase: LanguageUseCase,
    val breathingExercisesUseCase: BreathingExercisesUseCase
)