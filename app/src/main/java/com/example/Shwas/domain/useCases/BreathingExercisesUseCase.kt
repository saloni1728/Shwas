package com.example.Shwas.domain.useCases

import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity
import com.example.Shwas.domain.model.Failure
import com.example.Shwas.domain.model.Result
import com.example.Shwas.domain.repositories.IBreathingExerciseRepo
import kotlinx.coroutines.flow.Flow

class BreathingExercisesUseCase(
    private val breathingExerciseRepo: IBreathingExerciseRepo
) {

    fun getBreathingExercises() = breathingExerciseRepo.getBreathingExercises()

    fun getBreathingExercisesStep(id: String) = breathingExerciseRepo.getBreathingExercisesStep(id)

    fun setFavoriteExercise(id: String, open: Int): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>> = breathingExerciseRepo.setFavoriteExercise(id, open)

    fun setExpandedExercise(id: String, open: Int): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>> = breathingExerciseRepo.setExpandedExercise(id, open)
//
//    suspend fun getBreathingExerciseById(id: String) = breathingExerciseRepo.getBreathingExerciseById(id)
//
//    suspend fun saveBreathingExercise(breathingExercise: BreathingExercise) =
//        breathingExerciseRepo.saveBreathingExercise(breathingExercise)
//
//    suspend fun deleteBreathingExercise(id: String) = breathingExerciseRepo.deleteBreathingExercise(id)

}