package com.binar.belajarjetpackcompose.api

import com.binar.belajarjetpackcompose.dataclass.Game
import retrofit2.Response
import retrofit2.http.GET

interface GameApiService {
    @GET("/games")
    suspend fun getGames(): Response<List<Game>>
}