package com.example.Shwas.data.dataPersistence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.Shwas.utils.Constants.PREFERENCES_DATASTORE_NAME
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    val context: Context,
    val moshi: Moshi
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_DATASTORE_NAME)

    val dataStore: DataStore<Preferences> by lazy {
        context.dataStore
    }

    suspend fun <T> getValue(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is Int -> dataStore.data.map { preferences ->
                preferences[intPreferencesKey(key)] ?: defaultValue
            }.first()

            is Float -> dataStore.data.map { preferences ->
                preferences[floatPreferencesKey(key)] ?: defaultValue
            }.first()

            is Long -> dataStore.data.map { preferences ->
                preferences[longPreferencesKey(key)] ?: defaultValue
            }.first()

            is Double -> dataStore.data.map { preferences ->
                preferences[floatPreferencesKey(key)]?.toDouble() ?: defaultValue
            }.first()

            is String -> dataStore.data.map { preferences ->
                preferences[stringPreferencesKey(key)] ?: defaultValue
            }.first()

            is Boolean -> dataStore.data.map { preferences ->
                preferences[booleanPreferencesKey(key)] ?: defaultValue
            }.first()

            else -> throw IllegalArgumentException("Unsupported type")
        } as T
    }

    suspend fun <T> setValue(key: String, value: T) {
        try {
            dataStore.edit { preferences ->
                when (value) {
                    is Int -> preferences[intPreferencesKey(key)] = value
                    is Float -> preferences[floatPreferencesKey(key)] = value
                    is Long -> preferences[longPreferencesKey(key)] = value
                    is Double -> preferences[floatPreferencesKey(key)] = value.toFloat()
                    is String -> preferences[stringPreferencesKey(key)] = value
                    is Boolean -> preferences[booleanPreferencesKey(key)] = value
                    else -> preferences[stringPreferencesKey(key)] = value.toString()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend inline fun <reified T> storeObject(key: String, value: T) {
        val jsonObj = moshi.adapter(T::class.java).toJson(value)
        try {
            dataStore.edit { preferences ->
                preferences[stringPreferencesKey(key)] = jsonObj
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend inline fun <reified T> getObject(key: String): T? {
        return try {
            val jsonObj = dataStore.data.map { preferences ->
                preferences[stringPreferencesKey(key)]
            }.first()
            moshi.adapter(T::class.java).fromJson(jsonObj)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}