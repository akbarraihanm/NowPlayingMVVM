package com.example.testevermos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testevermos.R
import com.example.testevermos.databinding.ListMovieBinding
import com.example.testevermos.models.NowPlayingData
import com.example.testevermos.viewmodels.ListMovieViewModel

class ListMovieAdapter(private val listMovie: MutableList<NowPlayingData>) :
    RecyclerView.Adapter<ListMovieAdapter.ListMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        val binding = DataBindingUtil.inflate<ListMovieBinding>(LayoutInflater.from(parent.context),
        R.layout.list_movie,parent,false)
        return ListMovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    class ListMovieViewHolder(private val binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(npd : NowPlayingData) {
            val viewModel = ListMovieViewModel(npd)
            binding.listMovie = viewModel
        }
    }
}