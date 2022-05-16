package com.example.happydog.data.remote

import com.example.happydog.model.BreedImageResponse
import com.example.happydog.model.BreedsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {

    @GET("breeds/list/all")
    suspend fun getDogBreeds(): Response<BreedsResponse>

    @GET("breed/{breedName}/images/random")
    suspend fun getBreedImage(@Path("breedName") breedName: String): Response<BreedImageResponse>
}