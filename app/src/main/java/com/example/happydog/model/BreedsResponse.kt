package com.example.happydog.model

import com.google.gson.annotations.SerializedName

data class BreedsResponse (
    @SerializedName("message")
    val message: Map<String, List<String>>,
    @SerializedName("status")
    val status: String

)