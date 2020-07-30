package com.jayantkhopale.kotlinreddit.api

import com.jayantkhopale.kotlinreddit.data.NewsResponse
import retrofit2.http.GET

interface NewsService {

    @GET("/r/Kotlin/.json")
    suspend fun getKotlinNews(): NewsResponse
}