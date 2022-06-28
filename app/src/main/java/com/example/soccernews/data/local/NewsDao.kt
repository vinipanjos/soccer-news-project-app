package com.example.soccernews.data.local

import androidx.room.*
import com.example.soccernews.domain.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg news: News)

    @Query("SELECT * FROM news WHERE favorite = :favorite")
    fun loadFavoriteNews(favorite: Boolean): List<News>

}