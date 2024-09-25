package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.NewsActivity
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.util.Resource

class BreakingNewsFragment : Fragment() {

     lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "BreakingNewsFragment"
    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

       newsAdapter.setOnItemCLickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(activity)
            }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}