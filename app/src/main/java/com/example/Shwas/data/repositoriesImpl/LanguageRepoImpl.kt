package com.example.Shwas.data.repositoriesImpl

import android.content.Context
import android.os.Build
import com.example.Shwas.data.dataPersistence.PreferencesDataStore
import com.example.Shwas.data.dataPersistence.RoomDb.database.ShwasDataBase
import com.example.Shwas.data.dataPersistence.RoomDb.entity.LanguageEntity
import com.example.Shwas.data.dataPersistence.SharedPrefHelper
import com.example.Shwas.domain.model.Failure
import com.example.Shwas.domain.model.Result
import com.example.Shwas.domain.repositories.ILanguageRepo
import com.example.Shwas.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale
import javax.inject.Inject

class LanguageRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesDataStore: PreferencesDataStore,
    private val sharedPrefHelper: SharedPrefHelper,
    private val ShwasDataBase: ShwasDataBase
): ILanguageRepo {

    override suspend fun getLanguage(): String {
        return preferencesDataStore.getValue(Constants.PREFERRED_LANGUAGE, Constants.DEFAULT_LANGUAGE)
    }

    override suspend fun setLocale(language: String): Context {
        preferencesDataStore.setValue(Constants.PREFERRED_LANGUAGE, language)
        sharedPrefHelper.putValue(Constants.PREFERRED_LANGUAGE, language)
        ShwasDataBase.languageDao().selectLanguage(language)
        val newLocale = Locale(language)
        Locale.setDefault(newLocale)
        val config = context.resources.configuration

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ uses setLocales()
            config.setLocales(android.os.LocaleList(newLocale))
            context.createConfigurationContext(config)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Android 7.0+
            config.setLocale(newLocale)
            config.setLocales(android.os.LocaleList(newLocale))
            context.createConfigurationContext(config)
        } else {
            // Below Android 7
            config.setLocale(newLocale)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            context
        }
    }

    override fun fetchLanguages(): Flow<Result<List<LanguageEntity>, Failure<String>>> {
        return flow {
            try {
                val languages = ShwasDataBase.languageDao().getAllLanguages()
                emit(Result.Success(languages))
            } catch (e: Exception) {
                emit(Result.Error(Failure.UnknownError("Failed to fetch languages: ${e.message}")))
            }
        }
    }

}