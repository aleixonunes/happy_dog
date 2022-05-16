package com.example.happydog.utils


interface ExpandableBreeds {
    fun getItemType(): Int

    fun isExpanded(): Boolean

    fun setIsExpanded(expanded: Boolean)
}

const val PARENT = 0
const val CHILD = 1

data class Parent(val breedsName: String, val childItems: List<Child>) : ExpandableBreeds {

    private var isExpanded = false

    override fun getItemType() = PARENT
    override fun isExpanded(): Boolean {
        return isExpanded
    }

    override fun setIsExpanded(expanded: Boolean) {
        isExpanded = expanded
    }
}

data class Child(
    val breedsSubName: String
) : ExpandableBreeds {

    override fun getItemType() = CHILD
    override fun isExpanded(): Boolean {
        return false
    }

    override fun setIsExpanded(expanded: Boolean) {
        return
    }
}