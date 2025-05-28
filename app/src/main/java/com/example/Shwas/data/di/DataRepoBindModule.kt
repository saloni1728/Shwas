package com.example.Shwas.data.di

import com.example.Shwas.data.repositoriesImpl.BreathingExerciseRepoImpl
import com.example.Shwas.data.repositoriesImpl.LanguageRepoImpl
import com.example.Shwas.domain.repositories.IBreathingExerciseRepo
import com.example.Shwas.domain.repositories.ILanguageRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataRepoBindModule {

    @Binds
    abstract fun bindLanguageRepository(languageRepoImpl: LanguageRepoImpl): ILanguageRepo

    @Binds
    abstract fun bindBreathingExerciseRepository(breathingExerciseRepoImpl: BreathingExerciseRepoImpl): IBreathingExerciseRepo
}