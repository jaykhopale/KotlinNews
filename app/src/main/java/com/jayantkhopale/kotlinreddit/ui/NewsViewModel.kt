package com.jayantkhopale.kotlinreddit.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jayantkhopale.kotlinreddit.api.NewsRepository
import com.jayantkhopale.kotlinreddit.api.NewsResult
import com.jayantkhopale.kotlinreddit.data.ArticleData
import kotlinx.coroutines.Dispatchers

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val kotlinNews: LiveData<NewsResult> = liveData(viewModelScope.coroutineContext +
            Dispatchers.IO) {
        emit(NewsResult.Loading)
        try {
            val articles: List<ArticleData> = newsRepository.getKotlinNews().data?.children?.map {
                it.articleData } ?: listOf<ArticleData>()
            emit(NewsResult.Success(articles))
        } catch (exception: Exception) {
            emit(NewsResult.Failure(exception))
        }
    }
}