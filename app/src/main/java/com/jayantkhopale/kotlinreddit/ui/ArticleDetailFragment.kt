package com.jayantkhopale.kotlinreddit.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.jayantkhopale.kotlinreddit.R
import com.jayantkhopale.kotlinreddit.databinding.ArticleDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : Fragment(R.layout.article_detail_fragment) {

    private val newsViewModel: NewsViewModel by navGraphViewModels(R.id.kotlin_news_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ArticleDetailFragmentBinding.bind(view)
        newsViewModel.newsArticle.observe(viewLifecycleOwner, Observer { articleData ->
            (requireActivity() as NewsActivity).supportActionBar?.title = articleData.title
            binding.articleBody.text = articleData.selftext
            if (!TextUtils.isEmpty(articleData.getThumbnailUrl()) &&
                articleData.getThumbailAspectRatio() != null) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(binding.articleDetailContainer)
                constraintSet.setDimensionRatio(binding.articleImage.id,
                    articleData.getThumbailAspectRatio().toString())
                constraintSet.applyTo(binding.articleDetailContainer)
                Glide.with(binding.articleImage)
                    .load(articleData.getThumbnailUrl())
                    .into(binding.articleImage)
                binding.articleImage.visibility = View.VISIBLE
            }
        })
    }
}