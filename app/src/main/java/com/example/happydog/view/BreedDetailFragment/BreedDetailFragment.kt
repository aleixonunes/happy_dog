package com.example.happydog.view.BreedDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.happydog.databinding.FragmentBreedDetailBinding
import com.example.happydog.utils.NetworkResult
import com.example.happydog.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedDetailFragment : Fragment() {

    private var _binding: FragmentBreedDetailBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    private var breedName: String? = ""
    private var adapter: BreedsPicturesAdapter? = null

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
        adapter = BreedsPicturesAdapter(context = requireContext())
        binding.breedsPicRV.adapter = adapter
        fetchData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = breedName
    }

    private fun fetchData() {
        fetchResponse()
        mainViewModel.responseImages.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success<*> -> {
                    handleResponse(
                        response.message!!.message.asSequence().shuffled().take(10).toList()
                    )
                }
                is NetworkResult.Error<*> -> {
                    handleErrorResponse()
                }
                is NetworkResult.Loading<*> -> {
                    binding.loadBreedsPB.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun fetchResponse() {
        mainViewModel.fetchBreedImageResponse(breedName!!)
        binding.loadBreedsPB.visibility = View.VISIBLE
    }

    private fun handleResponse(breedsImagesUrlList: List<String>) {
        adapter?.setPicturesList(ArrayList(breedsImagesUrlList))
        binding.loadBreedsPB.visibility = View.GONE
    }

    private fun handleErrorResponse() {
        Toast.makeText(context, "An error occurred. Please try again", Toast.LENGTH_LONG)
            .show()
        binding.loadBreedsPB.visibility = View.GONE
    }
}