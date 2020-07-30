package com.jayantkhopale.kotlinreddit.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jayantkhopale.kotlinreddit.api.NewsRepository
import com.jayantkhopale.kotlinreddit.api.NewsResult
import com.jayantkhopale.kotlinreddit.data.ArticleData
import kotlinx.coroutines.Dispatchers

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsArticle: LiveData<ArticleData>
    get() = _newsArticle
    private val _newsArticle = MutableLiveData<ArticleData>()

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

    fun setArticle(articleData: ArticleData) {
        _newsArticle.value = articleData
    }

}