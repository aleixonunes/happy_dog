package com.example.happydog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.happydog.data.Repository
import com.example.happydog.model.BreedsResponse
import com.example.happydog.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<BreedsResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<BreedsResponse>> = _response

    fun fetchBreedsResponse() = viewModelScope.launch {
        repository.getDogBreeds().collect { values ->
            _response.value = values
        }
    }

}