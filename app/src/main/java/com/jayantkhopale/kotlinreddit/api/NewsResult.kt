package com.jayantkhopale.kotlinreddit.api

import com.jayantkhopale.kotlinreddit.data.ArticleData
import com.jayantkhopale.kotlinreddit.data.NewsResponse

sealed class NewsResult {
    object Loading : NewsResult()
    data class Success(val articles: List<ArticleData>) : NewsResult()
    data class Failure(val exception: Throwable) : NewsResult()
}