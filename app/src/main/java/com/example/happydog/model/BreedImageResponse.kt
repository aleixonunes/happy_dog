package com.example.happydog.model

import com.google.gson.annotations.SerializedName

data class BreedImageResponse (
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)