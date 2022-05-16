package com.example.happydog.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happydog.databinding.ExpandableChildItemBinding
import com.example.happydog.databinding.ExpandableParentItemBinding
import com.example.happydog.utils.*

class BreedsAdapter(private var breedsModelList: ArrayList<ExpandableBreeds> = arrayListOf(),
private val onBreedsClickListener: OnBreedsClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PARENT -> {
                val binding =
                    ExpandableParentItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                BreedsParentViewHolder(binding)
            }
            CHILD -> {
                val binding =
                    ExpandableChildItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                BreedsChildViewHolder(binding)
            }
            else -> {
                val binding =
                    ExpandableParentItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                BreedsParentViewHolder(binding)
            }

        }

    }

    fun setBreedsList(breedsList: ArrayList<ExpandableBreeds>) {
        breedsModelList = breedsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            PARENT -> {
                val parentHolder = holder as BreedsParentViewHolder
                parentHolder.breedParent = breedsModelList[position] as Parent
                parentHolder.bind()
            }

            CHILD -> {
                val childHolder = holder as BreedsChildViewHolder
                childHolder.breedChild = breedsModelList[position] as Child
                childHolder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int = breedsModelList[position].getItemType()

    override fun getItemCount(): Int {
        return breedsModelList.size
    }

    inner class BreedsParentViewHolder(private val binding: ExpandableParentItemBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        lateinit var breedParent: Parent

        init {
            binding.breedsName.setOnClickListener(this)
            binding.closeArrow.setOnClickListener {
                val startPosition = absoluteAdapterPosition + 1
                val count = breedParent.childItems.size

                if (breedParent.isExpanded()) {
                    breedsModelList.removeAll(breedParent.childItems)
                    notifyItemRangeRemoved(startPosition, count)
                    breedParent.setIsExpanded(false)
                } else {
                    breedsModelList.addAll(startPosition, breedParent.childItems)
                    notifyItemRangeInserted(startPosition, count)
                    breedParent.setIsExpanded(true)
                }

                binding.closeArrow.rotation = if (breedParent.isExpanded()) 180f else 0f
            }
        }

        fun bind() {
            binding.breedsName.text = breedParent.breedsName
            binding.closeArrow.visibility = if (breedParent.childItems.isEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }

            binding.closeArrow.rotation = if (breedParent.isExpanded()) 180f else 0f
        }

        override fun onClick(p0: View?) {
            onBreedsClickListener.onBreedClick(absoluteAdapterPosition)
        }

    }

    inner class BreedsChildViewHolder(private val binding: ExpandableChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var breedChild: Child

        fun bind() {
            binding.breedsName.text = breedChild.breedsSubName
        }

    }

    interface OnBreedsClickListener {
        fun onBreedClick(position: Int)
    }
}
