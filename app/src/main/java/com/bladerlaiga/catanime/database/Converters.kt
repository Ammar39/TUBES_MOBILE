package com.bladerlaiga.catanime.database

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
  @TypeConverter
  fun fromListString(value : List<String>) = Json.encodeToString(value)

  @TypeConverter
  fun fromListAnime(value : List<Anime>) = Json.encodeToString(value)

  @TypeConverter
  fun toListString(value: String) = Json.decodeFromString<List<String>>(value)

  @TypeConverter
  fun toListAnime(value: String) = Json.decodeFromString<List<Anime>>(value)
}