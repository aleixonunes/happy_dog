package com.example.happydog.data

import com.example.happydog.data.remote.RemoteDataSource
import com.example.happydog.model.BaseApiResponse
import com.example.happydog.model.BreedImageResponse
import com.example.happydog.model.BreedsResponse
import com.example.happydog.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getDogBreeds(): Flow<NetworkResult<BreedsResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getDogsBreeds() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getBreedsImage(breedName: String): Flow<NetworkResult<BreedImageResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getBreedsImage(breedName) })
        }.flowOn(Dispatchers.IO)
    }
}