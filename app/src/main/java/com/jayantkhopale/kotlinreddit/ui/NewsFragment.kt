package com.jayantkhopale.kotlinreddit.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jayantkhopale.kotlinreddit.R
import com.jayantkhopale.kotlinreddit.api.NewsResult
import com.jayantkhopale.kotlinreddit.databinding.NewsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.news_fragment) {

    private var newsFragmentBinding: NewsFragmentBinding? = null

    private val newsViewModel: NewsViewModel by navGraphViewModels(R.id.kotlin_news_graph) {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewsFragmentBinding.bind(view)
        newsFragmentBinding = binding

        val newsAdapter = NewsAdapter()
        newsAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        val listLayoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        binding.articlesList.apply {
            adapter = newsAdapter
            layoutManager = listLayoutManager
        }

        newsViewModel.kotlinNews.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is NewsResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NewsResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.submitList(result.articles)
                }
                is NewsResult.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        newsFragmentBinding = null
    }

}