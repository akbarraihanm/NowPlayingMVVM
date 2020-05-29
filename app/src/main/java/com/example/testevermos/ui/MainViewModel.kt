package com.example.testevermos.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testevermos.models.NowPlayingResponse
import com.example.testevermos.repository.MainRepository

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private val repository = MainRepository()

    var listMovie : MutableLiveData<NowPlayingResponse> = MutableLiveData()
    var error : MutableLiveData<Throwable> = MutableLiveData()

    fun getListMovie(page : String) {
        repository.requestListMovie({
            listMovie.postValue(it)
        },{
            error.postValue(it)
        }, page)
    }

    override fun onCleared() {
        super.onCleared()
        repository.onDestroy()
    }
}