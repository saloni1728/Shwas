package com.example.Shwas.data.dataPersistence.RoomDb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.Shwas.data.dataPersistence.RoomDb.dao.BreathingExerciseDao
import com.example.Shwas.data.dataPersistence.RoomDb.dao.BreathingStepsDao
import com.example.Shwas.data.dataPersistence.RoomDb.dao.LanguageDao
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity
import com.example.Shwas.data.dataPersistence.RoomDb.entity.LanguageEntity
import com.example.Shwas.data.dataPersistence.RoomDb.typeConverter.MapOfStringConverter
import com.example.Shwas.data.dataPersistence.RoomDb.typeConverter.MapOfStringLongConverter

@Database(
    entities = [
        LanguageEntity::class,
        BreathingExerciseEntity::class,
        BreathingStepsEntity::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(MapOfStringConverter::class, MapOfStringLongConverter::class)
abstract class ShwasDataBase: RoomDatabase() {
    abstract fun languageDao(): LanguageDao
    abstract fun breathingExerciseDao(): BreathingExerciseDao
    abstract fun breathingStepsDao(): BreathingStepsDao
}