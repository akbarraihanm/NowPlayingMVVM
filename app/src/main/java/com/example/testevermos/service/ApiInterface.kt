package com.example.testevermos.service

import com.example.testevermos.models.NowPlayingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey : String,
        @Query("page") page : String
    ) : Observable<NowPlayingResponse>

}