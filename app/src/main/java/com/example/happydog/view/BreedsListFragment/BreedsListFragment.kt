package com.example.happydog.view.BreedsListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.happydog.R
import com.example.happydog.databinding.FragmentBreedsListBinding
import com.example.happydog.model.BreedsResponse
import com.example.happydog.utils.Child
import com.example.happydog.utils.ExpandableBreeds
import com.example.happydog.utils.NetworkResult
import com.example.happydog.utils.Parent
import com.example.happydog.view.BreedDetailFragment.BreedDetailFragment
import com.example.happydog.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsListFragment : Fragment(), BreedsAdapter.OnBreedsClickListener {
    private lateinit var _binding: FragmentBreedsListBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private var adapter: BreedsAdapter? = null
    private val list : ArrayList<ExpandableBreeds> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsListBinding.inflate(inflater, container, false)
        adapter = BreedsAdapter(onBreedsClickListener = this)

        _binding.breedsRV.adapter = adapter
        fetchData()
        return _binding.root
    }

    private fun fetchData() {
        fetchResponse()

        mainViewModel.responseBreeds.observe(viewLifecycleOwner) { response ->
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
        mainViewModel.fetchBreedsResponse()
        _binding.loadBreedsPB.visibility = View.VISIBLE
    }

    private fun handleResponse(response: BreedsResponse) {
        for (breed in response.message) {
            val listChilds = mutableListOf<Child>()
            breed.value.forEach {
                listChilds.add(Child(it))
            }
            list.add(Parent(breed.key, listChilds))
        }

        adapter?.setBreedsList(list)
        _binding.loadBreedsPB.visibility = View.GONE
    }

    private fun handleErrorResponse() {
        Toast.makeText(context, "An error occurred. Please try again", Toast.LENGTH_LONG)
            .show()
        _binding.loadBreedsPB.visibility = View.GONE
    }

    override fun onBreedClick(position: Int) {
        val fragment = BreedDetailFragment()
        val bundle = Bundle().apply { putString("BreedName", (list[position] as Parent).breedsName ) }
        fragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }
}