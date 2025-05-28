package com.example.Shwas.data.dataPersistence.RoomDb.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class BreathingExerciseMigration {
    companion object {
        val MIGRATION_1_2 = object : Migration(startVersion = 1, endVersion = 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE breathing_exercise_new (
                            id TEXT PRIMARY KEY NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT NOT NULL,
                            instruction TEXT NOT NULL,
                            duration INTEGER NOT NULL,
                            difficulty TEXT NOT NULL,
                            card_color INTEGER NOT NULL,
                            text_color INTEGER NOT NULL,
                            accent_color INTEGER NOT NULL,
                            is_favourite INTEGER NOT NULL
                        )""".trimIndent())
                database.execSQL(
                    """INSERT INTO breathing_exercise_new (
                                id,
                                name,
                                description,
                                instruction,
                                duration,
                                difficulty,
                                card_color,
                                text_color,
                                accent_color,
                                is_favourite
                            )
                            SELECT 
                                id,
                                name,
                                description,
                                instruction,
                                duration,
                                difficulty,
                                CAST(card_color AS INTEGER),
                                CAST(text_color AS INTEGER),
                                CAST(accent_color AS INTEGER),
                                isFavourite
                            FROM breathing_exercise_entity""".trimIndent())
                database.execSQL("DROP TABLE breathing_exercise_entity")
                database.execSQL("ALTER TABLE breathing_exercise_new RENAME TO breathing_exercise_entity")
            }
        }

        val MIGRATION_2_3 = object : Migration(startVersion = 2, endVersion = 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""ALTER TABLE breathing_exercise_entity ADD COLUMN is_expanded INTEGER NOT NULL DEFAULT 0""")
            }
        }

        val MIGRATION_3_4 = object : Migration(startVersion = 3, endVersion = 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE IF NOT EXISTS breathing_steps_entity (
                        id TEXT NOT NULL PRIMARY KEY,
                        prep_prompt TEXT NOT NULL,
                        initial_prompt TEXT NOT NULL,
                        continue_prompt TEXT NOT NULL,
                        end_prompt TEXT NOT NULL,
                        number_of_cycle INTEGER NOT NULL,
                        FOREIGN KEY(id) REFERENCES breathing_exercise_entity(id) ON DELETE CASCADE
                    )""".trimIndent())
            }
        }
    }
}