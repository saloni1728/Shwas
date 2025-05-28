package com.example.Shwas.data.dataPersistence

import com.example.Shwas.data.dataPersistence.RoomDb.database.ShwasDataBase
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity
import com.example.Shwas.data.dataPersistence.RoomDb.entity.LanguageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseInitializer @Inject constructor(
    private val ShwasDataBase: ShwasDataBase
) {
    suspend fun initializeDatabase() {
        withContext(Dispatchers.IO) {
            ShwasDataBase.languageDao().insertAllLanguages(LanguageEntity.remoteData)
            ShwasDataBase.breathingExerciseDao().insertAllBreathingExercises(BreathingExerciseEntity.breathingExercises)
            ShwasDataBase.breathingStepsDao().insertBreathingSteps(BreathingStepsEntity.breathingExerciseSteps)
        }
    }

    suspend fun clearDatabase() {
        withContext(Dispatchers.IO) {
            ShwasDataBase.languageDao().deleteAllLanguages()
            ShwasDataBase.breathingExerciseDao().deleteAllBreathingExercises()
            ShwasDataBase.breathingStepsDao().deleteAllBreathingSteps()
        }

    }
}