package com.example.Shwas.data.dataPersistence.RoomDb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.Shwas.data.dataPersistence.RoomDb.entity.LanguageEntity

@Dao
interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLanguages(languages: List<LanguageEntity>)

    @Query("SELECT * FROM language_entity")
    fun getAllLanguages(): List<LanguageEntity>

    @Query("UPDATE language_entity SET selected = 0 WHERE selected = 1")
    fun deselectAllLanguages()

    @Query("UPDATE language_entity SET selected = 1 WHERE language_code = :languageCode")
    fun selectLanguage(languageCode: String)

    @Query("DELETE FROM language_entity")
    fun deleteAllLanguages()
}