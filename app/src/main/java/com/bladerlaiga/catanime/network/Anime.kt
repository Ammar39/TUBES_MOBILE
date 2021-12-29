package com.bladerlaiga.catanime.network

import com.google.gson.annotations.SerializedName
import java.lang.Exception

data class AnimeDetail(
  @SerializedName("mal_id")
  var id: Long = 0L,
  @SerializedName("url")
  var url: String = "",
  @SerializedName("title")
  val title: String = "",
  @SerializedName("title_japanese")
  val title_alt: String = "",
  @SerializedName("image_url")
  val image_url: String = "",
  @SerializedName("trailer_url")
  val trailer_url: String = "",
  @SerializedName("type")
  val type: String = "",
  @SerializedName("source")
  val source: String = "",
  @SerializedName("episodes")
  val episodes: Int = 0,
  @SerializedName("status")
  val status: String = "",
  @SerializedName("airing")
  val airing: Boolean = false,
  @SerializedName("aired")
  val aired: Aired,
  @SerializedName("duration")
  val duration: String = "",
  @SerializedName("rating")
  val rating: String = "",
  @SerializedName("score")
  val score: Float = 0F,
  @SerializedName("synopsis")
  val synopsis: String = "",
  @SerializedName("premiered")
  val premiered: String = "",
//  @SerializedName("related")
//  val related: String = "",
  @SerializedName("producers")
  val producers: List<Producer> = emptyList(),
  @SerializedName("studios")
  val studios: List<Studio> = emptyList(),
  @SerializedName("genres")
  val genres: List<Genre> = emptyList(),
  @SerializedName("themes")
  val themes: List<Theme> = emptyList(),
)
data class AnimeOverviewItem(
  @SerializedName("mal_id")
  var id: Long = 0L,
  @SerializedName("title")
  val title: String = "",
  @SerializedName("image_url")
  val image_url: String = "",
  @SerializedName("synopsis")
  val synopsis: String = "",
  @SerializedName("type")
  val type: String = "",
  @SerializedName("airing_start")
  val airing_start: String = "",
  @SerializedName("episodes")
  val episodes: Int = 0,
  @SerializedName("genres")
  val genres: List<Genre> = emptyList(),
  @SerializedName("themes")
  val themes: List<Theme> = emptyList(),
  @SerializedName("producers")
  val producers: List<Producer> = emptyList(),
  @SerializedName("source")
  val source: String = "",
  @SerializedName("score")
  val score: Float = 0F,
)
data class Aired(
  @SerializedName("string")
  val string: String = "",
)
data class Studio(
  @SerializedName("name")
  val name: String = "",
)
data class Genre(
  @SerializedName("name")
  val name: String = "",
)
data class Theme(
  @SerializedName("name")
  val name: String = "",
)
data class Producer(
  @SerializedName("name")
  val name: String = "",
)
data class AnimeOverview(
  @SerializedName("anime")
  val anime: List<AnimeOverviewItem>,
)

sealed class AnimeResult {
  data class Success(val data: List<AnimeOverviewItem>) : AnimeResult()
  data class Error(val error: Exception) : AnimeResult()
}
