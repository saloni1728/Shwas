package com.example.Shwas.data.dataPersistence.RoomDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language_entity")
data class LanguageEntity(
    @PrimaryKey
    @ColumnInfo(name = "language_code")
    val languageCode: String,
    @ColumnInfo(name = "language_name")
    val languageName: String,
    @ColumnInfo(name = "selected")
    val isSelected: Boolean = false
) {
    companion object {
        val remoteData = listOf(
            LanguageEntity(
                languageCode = "en",
                languageName = "English"
            ),
            LanguageEntity(
                languageCode = "hi",
                languageName = "हिंदी"
            )
        )
    }
}