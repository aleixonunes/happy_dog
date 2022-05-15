package com.example.happydog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.happydog.databinding.ActivityMainBinding
import com.example.happydog.model.BreedsResponse
import com.example.happydog.utils.NetworkResult
import com.example.happydog.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private var adapter: BreedsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        adapter = BreedsAdapter()
        _binding.breedsRV.adapter = adapter

        fetchData()
    }


    private fun fetchData() {
        fetchResponse()

        mainViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success<*> -> {
                    handleResponse(response.message!!)
                }

                is NetworkResult.Error<*> -> {
                    handleErrorResponse()
                }

                is NetworkResult.Loading<*> -> {
                }
            }
        }

    }

    private fun fetchResponse() {
        mainViewModel.fetchBreedsResponse()
    }

    private fun handleResponse(response: BreedsResponse) {
        adapter?.setBreedsList(response.message.toList().map { it.first })
        Toast.makeText(applicationContext, response.status, Toast.LENGTH_LONG).show()
    }

    private fun handleErrorResponse() {
        Toast.makeText(applicationContext, "An error occurred. Please try again", Toast.LENGTH_LONG)
            .show()
    }
}