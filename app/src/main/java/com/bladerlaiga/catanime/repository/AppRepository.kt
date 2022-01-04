package com.bladerlaiga.catanime.repository

import android.app.Application
import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bladerlaiga.catanime.AnimeOverviewItem
import com.bladerlaiga.catanime.database.*
import com.bladerlaiga.catanime.network.AnimeService
import com.bladerlaiga.catanime.network.AnimeService.Companion.service
import com.bladerlaiga.catanime.network.AnimeSource
import kotlinx.coroutines.flow.Flow

class AppRepository(context: Context) {
  private var animeDetailDAO: AnimeDetailDAO
  private var animeOverviewItemDAO: AnimeOverviewItemDAO
  private var animeFavoriteDAO: AnimeFavoriteDAO
  private var animeService: AnimeService
  private var animeReminderDAO: AnimeReminderDAO

  init {
    val database = AppDatabase.getInstance(context)

    animeDetailDAO = database.animeDetailDAO
    animeOverviewItemDAO = database.animeOverviewItemDAO
    animeFavoriteDAO = database.animeFavoriteDAO
    animeReminderDAO = database.animeReminderDAO
    animeService = AnimeService.getInstance()
  }

  fun getAnimeDetail(): AnimeDetailDAO {
    return animeDetailDAO
  }

  fun getAnimeOverviewItem(): AnimeOverviewItemDAO {
    return animeOverviewItemDAO
  }

  fun getAnimeFavorite(): AnimeFavoriteDAO {
    return animeFavoriteDAO
  }

  fun getAnimeReminder(): AnimeReminderDAO {
    return animeReminderDAO
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