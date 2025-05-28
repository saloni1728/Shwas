package com.example.Shwas.data.dataPersistence.RoomDb.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class MapOfStringLongConverter @Inject constructor(private val moshi: Moshi) {
    private val type = Types.newParameterizedType(
        Map::class.java,
        String::class.java,
        Types.newParameterizedType(Map::class.java, String::class.java, Long::class.javaObjectType)
    )
    private val adapter = moshi.adapter<Map<String, Map<String, Long>>>(type)

    @TypeConverter
    fun fromJson(json: String?): Map<String, Map<String, Long>>? {
        return json?.let { adapter.fromJson(it) }
    }

    @TypeConverter
    fun toJson(data: Map<String, Map<String, Long>>?): String? {
        return data?.let { adapter.toJson(it) }
    }
}