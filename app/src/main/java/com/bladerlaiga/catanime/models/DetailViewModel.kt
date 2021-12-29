package com.bladerlaiga.catanime.models

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bladerlaiga.catanime.repository.AppRepository

class DetailViewModel(
  application: Application
) : AndroidViewModel(application) {
  private var repository: AppRepository = AppRepository(application)
  private val animeDAO = repository.getAnimeDao()
  private val animeService = repository.getAnimeService()

  var title by mutableStateOf("")
  var image by mutableStateOf("")
  var title_alt by mutableStateOf("")
  var episode by mutableStateOf("")
  var status by mutableStateOf("")
  var aired by mutableStateOf("")
  var premiered by mutableStateOf("")
  var source by mutableStateOf("")
  var producer by mutableStateOf("")
  var studio by mutableStateOf("")
  var genre by mutableStateOf("")
  var theme by mutableStateOf("")
  var duration by mutableStateOf("")
  var rating by mutableStateOf("")
  var synopsis by mutableStateOf("")

  suspend fun loadFromDatabase(id: Long): Boolean {
    val data = animeDAO.get(id)
    if (data != null) {
      title = data.title
      image = data.image_url
      title_alt = data.alt_title
      episode = data.episodes.toString()
      status = data.status
      aired = data.airing_start
      source = data.source
      producer = data.producers
      genre = data.genres
      theme = data.themes
      duration = data.duration
      synopsis = data.synopsis
      return true
    }
    return false
  }

  suspend fun loadFromNetwork(id: Long) {
    val data = animeService.get(id = id)
    title = data.title
    image = data.image_url
    title_alt = data.title_alt
    episode = data.episodes.toString()
    status = data.status
    aired = data.aired.string
    premiered = data.premiered
    source = data.source
    producer = data.producers.joinToString { it.name }
    studio = data.studios.joinToString { it.name }
    genre = data.genres.joinToString { it.name }
    theme = data.themes.joinToString { it.name }
    duration = data.duration
    rating = data.rating
    synopsis = data.synopsis
  }
}

class DetailViewModelFactory(
  private val application: Application
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("Unchecked cast")
    if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
      return DetailViewModel(application) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}