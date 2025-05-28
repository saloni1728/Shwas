package com.example.Shwas.domain.repositories

import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity
import com.example.Shwas.domain.model.Failure
import com.example.Shwas.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface IBreathingExerciseRepo {
    fun getBreathingExercises(): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>>
    fun getBreathingExercisesStep(id: String): Flow<Result<BreathingStepsEntity, Failure<String>>>
    fun setFavoriteExercise(id: String, open: Int): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>>
    fun setExpandedExercise(id: String, open: Int): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>>
}