package com.example.Shwas.domain.repositories

import android.content.Context
import com.example.Shwas.data.dataPersistence.RoomDb.entity.LanguageEntity
import com.example.Shwas.domain.model.Failure
import com.example.Shwas.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ILanguageRepo {
    suspend fun getLanguage(): String
    suspend fun setLocale(language: String): Context
    fun fetchLanguages(): Flow<Result<List<LanguageEntity>, Failure<String>>>
}