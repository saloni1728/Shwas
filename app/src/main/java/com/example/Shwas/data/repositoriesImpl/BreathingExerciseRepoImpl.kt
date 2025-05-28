package com.example.Shwas.data.repositoriesImpl

import com.example.Shwas.data.dataPersistence.RoomDb.database.ShwasDataBase
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity
import com.example.Shwas.domain.model.Failure
import com.example.Shwas.domain.model.Result
import com.example.Shwas.domain.repositories.IBreathingExerciseRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BreathingExerciseRepoImpl @Inject constructor(
    private val ShwasDataBase: ShwasDataBase
): IBreathingExerciseRepo {
    override fun getBreathingExercises(): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>> = flow {
        try {
            val data = ShwasDataBase.breathingExerciseDao().getAllBreathingExercises()
            emit(Result.Success(data))
        } catch (exception: Exception) {
            emit(Result.Error(Failure.UnknownError(exception.message ?: "Unknown error occurred")))
        }
    }

    override fun getBreathingExercisesStep(id: String): Flow<Result<BreathingStepsEntity, Failure<String>>> = flow {
        try {
            val data = ShwasDataBase.breathingStepsDao().getBreathingSteps(id)
            emit(Result.Success(data))
        } catch (exception: Exception) {
            emit(Result.Error(Failure.UnknownError(exception.message ?: "Unknown error occurred")))
        }
    }

    override fun setFavoriteExercise(id: String, open: Int): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>> {
        return flow {
            try {
                val data = ShwasDataBase.breathingExerciseDao().updateFavAndFetchAll(id, open)
                emit(Result.Success(data))
            } catch (exception: Exception) {
                emit(Result.Error(Failure.UnknownError(exception.message ?: "Unknown error occurred")))
            }
        }
    }

    override fun setExpandedExercise(id: String, open: Int): Flow<Result<List<BreathingExerciseEntity>, Failure<String>>> {
        return flow {
            try {
                val data = ShwasDataBase.breathingExerciseDao().updateExpandedAndFetchAll(id, open)
                emit(Result.Success(data))
            } catch (exception: Exception) {
                emit(Result.Error(Failure.UnknownError(exception.message ?: "Unknown error occurred")))
            }
        }
    }
}