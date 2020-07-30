package com.jayantkhopale.kotlinreddit.ui

import com.jayantkhopale.kotlinreddit.data.ArticleData

interface ArticleClickListener {
    fun onArticleClicked(articleData: ArticleData)
}
