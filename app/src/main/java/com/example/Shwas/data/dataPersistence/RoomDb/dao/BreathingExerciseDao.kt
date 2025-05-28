package com.example.Shwas.data.dataPersistence.RoomDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity

@Dao
interface BreathingExerciseDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insertAllBreathingExercises(breathingExercises: List<BreathingExerciseEntity>)

    @Query("DELETE FROM breathing_exercise_entity")
    fun deleteAllBreathingExercises()

    @Query("SELECT * FROM breathing_exercise_entity")
    fun getAllBreathingExercises(): List<BreathingExerciseEntity>

    @Query("UPDATE breathing_exercise_entity SET is_favourite = :open WHERE id = :id")
    fun updateFavoriteExercise(id: String, open: Int)

    @Query("UPDATE breathing_exercise_entity SET is_expanded = :open WHERE id = :id")
    fun updateExpandedExercise(id: String, open: Int)

    @Transaction
    suspend fun updateFavAndFetchAll(id: String, open: Int): List<BreathingExerciseEntity> {
        updateFavoriteExercise(id, open)
        return getAllBreathingExercises()
    }

    @Transaction
    suspend fun updateExpandedAndFetchAll(id: String, open: Int): List<BreathingExerciseEntity> {
        updateExpandedExercise(id, open)
        return getAllBreathingExercises()
    }
}