package com.example.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class ResponseTvShow (
    @SerializedName("results")
    val resultListTvShow: ArrayList<TvShow>
)