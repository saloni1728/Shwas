package com.example.Shwas.data.dataPersistence.RoomDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity

@Dao
interface BreathingStepsDao {
    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insertBreathingSteps(steps: List<BreathingStepsEntity>)

    @Query("DELETE FROM breathing_steps_entity")
    fun deleteAllBreathingSteps()

    @Query("SELECT * FROM breathing_steps_entity WHERE id = :id")
    fun getBreathingSteps(id: String): BreathingStepsEntity
}