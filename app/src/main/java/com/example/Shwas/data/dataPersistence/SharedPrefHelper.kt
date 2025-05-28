package com.example.Shwas.data.dataPersistence

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.Shwas.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun <T> putValue(key: String, value: T) {
        try {
            preferences.edit {
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Float -> putFloat(key, value)
                    is Long -> putLong(key, value)
                    else -> putValue(key, value)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun <T> getValue(key: String, defaultValue: T): T {
        try {
            return when (defaultValue) {
                is String -> preferences.getString(key, defaultValue) as T
                is Int -> preferences.getInt(key, defaultValue) as T
                is Boolean -> preferences.getBoolean(key, defaultValue) as T
                is Float -> preferences.getFloat(key, defaultValue) as T
                is Long -> preferences.getLong(key, defaultValue) as T
                else -> throw IllegalArgumentException("Unsupported type")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return defaultValue
        }
    }

    fun clear() {
        preferences.edit { clear() }
    }

    fun remove(key: String) {
        preferences.edit { remove(key) }
    }
}