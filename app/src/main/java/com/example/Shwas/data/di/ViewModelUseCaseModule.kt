package com.example.Shwas.data.di

import com.example.Shwas.domain.model.AppUseCasesModel
import com.example.Shwas.domain.repositories.IBreathingExerciseRepo
import com.example.Shwas.domain.repositories.ILanguageRepo
import com.example.Shwas.domain.useCases.BreathingExercisesUseCase
import com.example.Shwas.domain.useCases.LanguageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class ViewModelUseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideUseCase(
        languageRepo: ILanguageRepo,
        breathingExerciseRepo: IBreathingExerciseRepo
    ) = AppUseCasesModel(
        languageUseCase = LanguageUseCase(languageRepo),
        breathingExercisesUseCase = BreathingExercisesUseCase(breathingExerciseRepo)
    )
}