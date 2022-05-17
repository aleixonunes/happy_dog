package com.example.happydog.view.BreedDetailFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.happydog.databinding.BreedPictureCardBinding

class BreedsPicturesAdapter(
    private var breedsPictureList: ArrayList<String> = arrayListOf(),
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            BreedPictureCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)

    }

    fun setPicturesList(breedsUrlsList: ArrayList<String>) {
        breedsPictureList = breedsUrlsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val parentHolder = holder as ViewHolder
        parentHolder.bind()
    }

    override fun getItemCount(): Int {
        return breedsPictureList.size
    }

    inner class ViewHolder(private val binding: BreedPictureCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            Glide
                .with(context)
                .load(breedsPictureList[absoluteAdapterPosition])
                .override(600,600)
                .centerCrop()
                .into(binding.breedIV)
            val textCounter = (absoluteAdapterPosition + 1).toString() + "/" + breedsPictureList.size
            binding.counterTV.text = textCounter
        }

    }
}

