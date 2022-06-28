package com.example.soccernews.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.soccernews.domain.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg news: News)

    @Query("SELECT * FROM news WHERE favorite = 1")
    fun loadFavoriteNews(): List<News>

}