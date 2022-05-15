package com.example.happydog.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happydog.databinding.CardViewLayoutBinding

class BreedsAdapter:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList = emptyList<String>()

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setBreedsList(breedsList: List<String>){
        mList = breedsList
        notifyDataSetChanged()
    }

    // Binds the list items to a view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mViewHolder = holder as ViewHolder
        mViewHolder.bind(mList[position])
    }

    // Return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(private val binding: CardViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(breedName: String) {
            binding.breedsTextView.text = breedName
        }
    }
}
