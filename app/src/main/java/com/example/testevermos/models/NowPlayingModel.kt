package com.example.testevermos.models

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("results")
    var result : ArrayList<NowPlayingData>? = null
)

data class NowPlayingData(
    @SerializedName("poster_path")
    var posterPath : String? = null
)