package com.example.testevermos.repository

import com.example.testevermos.models.NowPlayingResponse
import com.example.testevermos.service.ApiClient
import com.example.testevermos.service.ApiObserver
import com.example.testevermos.utils.ConstString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepository {

    private val apiService = ApiClient.getApi()
    private val compositeDisposable = CompositeDisposable()

    fun requestListMovie(listMovie : (NowPlayingResponse) -> Unit, onError : (Throwable) -> Unit
    ,page : String){
        apiService.getNowPlaying(ConstString().getApiKey(), page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<NowPlayingResponse>(compositeDisposable){
                override fun onApiSuccess(data: NowPlayingResponse) {
                    listMovie(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }

            })
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}