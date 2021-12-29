package com.bladerlaiga.catanime.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnimeDAO {
  @Insert
  suspend fun insert(anime: Anime)
  @Update
  suspend fun update(anime: Anime)
  @Delete
  suspend fun delete(anime: Anime)
  @Query("Select * From anime Where id = :id")
  suspend fun get(id: Long): Anime?
  @Query("Select * From anime")
  suspend fun getAll(): List<Anime>
}