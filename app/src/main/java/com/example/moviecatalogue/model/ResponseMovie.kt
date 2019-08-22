package com.example.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class ResponseMovie (
    @SerializedName("results")
    val resultListMovie : ArrayList<ListMovie>
)