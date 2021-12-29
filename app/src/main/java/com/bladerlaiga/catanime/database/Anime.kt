package com.bladerlaiga.catanime.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class Anime(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L,
  @ColumnInfo(name = "title")
  val title: String = "",
  @ColumnInfo(name = "image_url")
  val image_url: String = "",
  @ColumnInfo(name = "alt_title")
  val alt_title: String = "",
  @ColumnInfo(name = "synopsis")
  val synopsis: String = "",
  @ColumnInfo(name = "status")
  val status: String = "",
  @ColumnInfo(name = "type")
  val type: String = "",
  @ColumnInfo(name = "airing_start")
  val airing_start: String = "",
  @ColumnInfo(name = "episodes")
  val episodes: Int = 0,
  @ColumnInfo(name = "genres")
  val genres: String,
  @ColumnInfo(name = "themes")
  val themes: String = "",
  @ColumnInfo(name = "producers")
  val producers: String = "",
  @ColumnInfo(name = "source")
  val source: String = "",
  @ColumnInfo(name = "duration")
  val duration: String = "",
  @ColumnInfo(name = "score")
  val score: Int = 0,
)
