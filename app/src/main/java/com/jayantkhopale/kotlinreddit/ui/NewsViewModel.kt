package com.jayantkhopale.kotlinreddit.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jayantkhopale.kotlinreddit.api.NewsRepository
import com.jayantkhopale.kotlinreddit.api.NewsResult
import com.jayantkhopale.kotlinreddit.data.ArticleData
import kotlinx.coroutines.Dispatchers

/**
 * A [ViewModel] to fetch Kotlin news articles from Reddit. It uses [LiveData] to let the
 * view know about error states and successful responses.
 */
class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsArticle: LiveData<ArticleData>
    get() = _newsArticle
    private val _newsArticle = MutableLiveData<ArticleData>()

    /**
     * A [LiveData] object to notify the view of loading, success and error states for Kotlin
     * news articles.
     */
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