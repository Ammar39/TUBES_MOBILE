package com.bladerlaiga.catanime.models

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bladerlaiga.catanime.R
import com.bladerlaiga.catanime.network.AnimeOverviewItem
import com.bladerlaiga.catanime.repository.AppRepository

class HomeViewModel(
  application: Application
) : AndroidViewModel(application) {
  private var repository: AppRepository = AppRepository(application)
  private val animeService = repository.getAnimeService();
  private var data_year by mutableStateOf(2018)
  private var data_season by mutableStateOf("winter")
  var data by mutableStateOf(emptyList<AnimeOverviewItem>())
  var grid_type by mutableStateOf(GridType.List)
  var grid_count by mutableStateOf(1)
  var grid_id by mutableStateOf(R.drawable.ic_baseline_view_week_24)
  suspend fun loadFromNetwork() {
    val overview = animeService.getAll(data_year, data_season)
    data = overview.anime
  }
  suspend fun loadMoreFromNetwork() {
    data_year += 1
    data_season = when(data_season) {
      "winter" -> "spring"
      "spring" -> "summer"
      "summer" -> "fall"
      else -> "winter"
    }
    loadFromNetwork()
  }
  fun rotate() {
    if (grid_type == GridType.List) {
      grid_type = GridType.Column
      grid_count = 2
      grid_id = R.drawable.ic_round_view_module_24
    } else {
      grid_type = GridType.List
      grid_count = 1
      grid_id = R.drawable.ic_baseline_view_week_24
    }
  }
}

enum class GridType {
  List,
  Column
}

class HomeViewModelFactory(
  private val application: Application
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("Unchecked cast")
    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
      return HomeViewModel(application) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}