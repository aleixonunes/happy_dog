package com.example.happydog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.happydog.databinding.FragmentBreedDetailBinding
import com.example.happydog.model.BreedImageResponse
import com.example.happydog.model.BreedsResponse
import com.example.happydog.utils.Child
import com.example.happydog.utils.NetworkResult
import com.example.happydog.utils.Parent
import com.example.happydog.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BreedDetailFragment: Fragment() {

    private lateinit var _binding: FragmentBreedDetailBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private var breedName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            breedName = it.getString("BreedName")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedDetailBinding.inflate(inflater, container, false)
        _binding.breedsName.text = breedName
        fetchData()

        return _binding.root
    }

    private fun fetchData() {
        fetchResponse()

        mainViewModel.responseImages.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success<*> -> {
                    handleResponse(response.message!!)
                }

                is NetworkResult.Error<*> -> {
                    handleErrorResponse()
                }

                is NetworkResult.Loading<*> -> {
                    _binding.loadBreedsPB.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun fetchResponse() {
        mainViewModel.fetchBreedImageResponse(breedName!!)
        _binding.loadBreedsPB.visibility = View.VISIBLE
    }

    private fun handleResponse(response: BreedImageResponse) {
        Glide.with(this).load(response.message).into(_binding.iv);


       // adapter?.setBreedsList(list)
        _binding.loadBreedsPB.visibility = View.GONE
    }

    private fun handleErrorResponse() {
        Toast.makeText(context, "An error occurred. Please try again", Toast.LENGTH_LONG)
            .show()
        _binding.loadBreedsPB.visibility = View.GONE
    }
}