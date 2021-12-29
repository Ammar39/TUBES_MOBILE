package com.bladerlaiga.catanime.repository

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bladerlaiga.catanime.database.AnimeDAO
import com.bladerlaiga.catanime.database.AppDatabase
import com.bladerlaiga.catanime.network.AnimeOverviewItem
import com.bladerlaiga.catanime.network.AnimeService
import com.bladerlaiga.catanime.network.AnimeService.Companion.service
import com.bladerlaiga.catanime.network.AnimeSource
import kotlinx.coroutines.flow.Flow

class AppRepository(application: Application) {
  private var animeDAO: AnimeDAO
  private var animeService: AnimeService

  init {
    val database = AppDatabase.getInstance(application.applicationContext)
    animeDAO = database.animeDAO
    animeService = AnimeService.getInstance()
  }

  fun getAnimeDao(): AnimeDAO {
    return animeDAO
  }

  fun getAnimeService(): AnimeService {
    return animeService
  }

  fun getSearchResultStream(query: String): Flow<PagingData<AnimeOverviewItem>> {
    return Pager(
      config = PagingConfig(
        pageSize = NETWORK_PAGE_SIZE,
        enablePlaceholders = false
      ),
      pagingSourceFactory = { AnimeSource(service!!/*, query*/) }
    ).flow
  }

  companion object {
    const val NETWORK_PAGE_SIZE = 50
  }
}