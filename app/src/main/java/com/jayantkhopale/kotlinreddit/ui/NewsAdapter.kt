package com.jayantkhopale.kotlinreddit.ui

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jayantkhopale.kotlinreddit.data.ArticleData
import com.jayantkhopale.kotlinreddit.data.ArticleDiffCallback
import com.jayantkhopale.kotlinreddit.databinding.ItemNewsArticleBinding

class NewsAdapter : ListAdapter<ArticleData, NewsAdapter.NewsViewHolder>(ArticleDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = ItemNewsArticleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsViewHolder(private val itemNewsArticleBinding: ItemNewsArticleBinding) :
        RecyclerView.ViewHolder(itemNewsArticleBinding.root) {

        fun bind(article: ArticleData) {
            itemNewsArticleBinding.articleTitle.text = article.title
            if (!TextUtils.isEmpty(article.getThumbnailUrl()) && article.getThumbailAspectRatio() != null) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(itemNewsArticleBinding.articleContainer)
                constraintSet.setDimensionRatio(itemNewsArticleBinding.articleImage.id, article.getThumbailAspectRatio().toString())
                constraintSet.applyTo(itemNewsArticleBinding.articleContainer)
                Glide.with(itemNewsArticleBinding.articleImage)
                    .load(article.getThumbnailUrl())
                    .into(itemNewsArticleBinding.articleImage)
                itemNewsArticleBinding.articleImage.visibility = View.VISIBLE
            } else {
                itemNewsArticleBinding.articleImage.visibility = View.GONE
            }
        }
    }
}