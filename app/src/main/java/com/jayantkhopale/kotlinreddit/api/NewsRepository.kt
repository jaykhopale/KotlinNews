package com.jayantkhopale.kotlinreddit.api

import com.jayantkhopale.kotlinreddit.data.NewsResponse
import javax.inject.Inject

/**
 * A repository to fetch Kotlin news articles.
 */
class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getKotlinNews(): NewsResponse {
        return newsService.getKotlinNews()
    }
}