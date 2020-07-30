package com.jayantkhopale.kotlinreddit.api

import com.jayantkhopale.kotlinreddit.data.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getKotlinNews(): NewsResponse {
        return newsService.getKotlinNews()
    }
}