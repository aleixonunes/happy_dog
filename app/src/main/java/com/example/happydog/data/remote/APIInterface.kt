package com.example.happydog.data.remote

import com.example.happydog.model.BreedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {

    @GET("breeds/list/all")
    suspend fun getDogBreeds(): Response<BreedsResponse>
}