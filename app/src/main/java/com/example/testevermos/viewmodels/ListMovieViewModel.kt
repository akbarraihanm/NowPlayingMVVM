package com.example.testevermos.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.testevermos.models.NowPlayingData
import com.example.testevermos.utils.ConstString

class ListMovieViewModel(movieData : NowPlayingData) : ViewModel() {

    var imageUrl : ObservableField<String> = ObservableField()

    init {
        imageUrl.set(ConstString().posterLink()+movieData.posterPath)
    }
}