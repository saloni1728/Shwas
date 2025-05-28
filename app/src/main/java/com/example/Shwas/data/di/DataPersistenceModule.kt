package com.example.Shwas.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.Shwas.data.dataPersistence.PreferencesDataStore
import com.example.Shwas.data.dataPersistence.RoomDb.database.ShwasDataBase
import com.example.Shwas.data.dataPersistence.RoomDb.migration.BreathingExerciseMigration
import com.example.Shwas.data.dataPersistence.RoomDb.typeConverter.MapOfStringConverter
import com.example.Shwas.data.dataPersistence.RoomDb.typeConverter.MapOfStringLongConverter
import com.example.Shwas.data.dataPersistence.SharedPrefHelper
import com.example.Shwas.utils.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataPersistenceModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): PreferencesDataStore = PreferencesDataStore(context, moshi)

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPrefHelper = SharedPrefHelper(context)

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
        mapOfStringConverter: MapOfStringConverter,
        mapOfStringLongConverter: MapOfStringLongConverter
    ): ShwasDataBase = Room.databaseBuilder(
        context = context,
        klass = ShwasDataBase::class.java, Constants.DATABASE_NAME
    ).addTypeConverter(mapOfStringLongConverter)
        .addTypeConverter(mapOfStringConverter)
        .addMigrations(
            BreathingExerciseMigration.MIGRATION_1_2,
            BreathingExerciseMigration.MIGRATION_2_3,
            BreathingExerciseMigration.MIGRATION_3_4
        ).build()
}