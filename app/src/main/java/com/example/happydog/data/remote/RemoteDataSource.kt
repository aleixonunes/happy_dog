package com.example.happydog.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIInterface) {
    suspend fun getDogsBreeds() =
        apiService.getDogBreeds()

    suspend fun getBreedsImage(breed : String) =
        apiService.getBreedImage(breed)
}