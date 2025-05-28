package com.example.Shwas.data.dataPersistence.RoomDb.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class MapOfStringConverter @Inject constructor(private val moshi: Moshi) {
    @TypeConverter
    fun fromMap(map: Map<String, String>?): String? {
        val type = Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            String::class.java
        )
        return moshi.adapter<Map<String, String>>(type).toJson(map)
    }

    @TypeConverter
    fun toMap(json: String?): Map<String, String>? {
        return if (json.isNullOrEmpty()) {
            null
        } else {
            val type = Types.newParameterizedType(
                Map::class.java,
                String::class.java,
                String::class.java
            )
            moshi.adapter<Map<String, String>>(type).fromJson(json)
        }
    }
}