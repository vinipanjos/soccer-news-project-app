package com.example.soccernews.data.remote

import com.example.soccernews.domain.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface SoccerNewsApi {

    @GET("news.json")
    fun getNews(): Call<List<News>>

}