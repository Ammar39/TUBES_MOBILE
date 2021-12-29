package com.bladerlaiga.catanime.models

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bladerlaiga.catanime.database.Anime
import com.bladerlaiga.catanime.repository.AppRepository

class EditViewModel(
  application: Application
) : AndroidViewModel(application) {
  private var repository: AppRepository = AppRepository(application)
  private val animeDAO = repository.getAnimeDao()

  var title by mutableStateOf("")
  var image by mutableStateOf("")
  var bitmap by mutableStateOf<Bitmap?>(null)
  var alt_name by mutableStateOf("")
  var episode by mutableStateOf(0)
  var status by mutableStateOf("")
  var aired by mutableStateOf("")
  var premiered by mutableStateOf("")
  var source by mutableStateOf("")
  var producers by mutableStateOf("")
  var studios by mutableStateOf("")
  var genre by mutableStateOf("")
  var theme by mutableStateOf("")
  var duration by mutableStateOf("")
  var synopsis by mutableStateOf("")

  fun reset() {
    title = ""
    image = ""
    bitmap = null
    alt_name = ""
    episode = 0
    status = ""
    aired = ""
    premiered = ""
    source = ""
    producers = ""
    studios = ""
    genre = ""
    theme = ""
    duration = ""
    synopsis = ""
  }
  suspend fun save() {
    animeDAO.insert(
      Anime(
        title = title,
        image_url = image,
        alt_title = alt_name,
        episodes = episode,
        status = status,
        airing_start = aired,
        source = source,
        producers = producers,
        genres = genre,
        themes = theme,
        duration = duration,
        synopsis = synopsis
      )
    )
  }
}

class EditViewModelFactory(
  private val application: Application
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("Unchecked cast")
    if (modelClass.isAssignableFrom(EditViewModel::class.java)) {
      return EditViewModel(application) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}