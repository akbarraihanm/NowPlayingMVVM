package com.example.testevermos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testevermos.R
import com.example.testevermos.databinding.ActivityMainBinding
import com.example.testevermos.models.NowPlayingData
import com.example.testevermos.utils.PaginationScrollListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var adapter: ListMovieAdapter
    private lateinit var listMovie : MutableList<NowPlayingData>

    companion object {
        var page = 1
        var isProcessing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        listMovie = mutableListOf()
        viewModel.getListMovie("$page")

        setupRecyclerView()
        observeListMovie()
        paginationScroll()
    }

    private fun paginationScroll() {
        val isLastPage = false
        binding.rvMovie.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun loadMoreItem() {
                if(!isProcessing) {
                    isProcessing = true
                    page++
                    viewModel.getListMovie("$page")
                }
            }

        })
    }

    private fun observeListMovie() {
        viewModel.listMovie.observe(this, Observer {
            if(it.result?.isNotEmpty()!!){
                listMovie.addAll(it.result!!)
                adapter = ListMovieAdapter(listMovie)
                binding.rvMovie.adapter = adapter
                adapter.notifyDataSetChanged()
                layoutManager.scrollToPosition(adapter.itemCount- it.result?.size!!)
                isProcessing = false
            }
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupRecyclerView() {
        layoutManager = GridLayoutManager(this,3)
        binding.rvMovie.layoutManager = layoutManager
    }
}
