package com.example.sherbek791.screens.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sherbek791.databinding.CategoryRecyclerViewBinding
import com.example.sherbek791.screens.category.modules.CategoryModule
import javax.inject.Inject

class CategoryPagingAdapter  : PagingDataAdapter<CategoryModule.CategoryModuleItem,CategoryPagingAdapter.ViewHolder>(
    diffUtil) {

    private var onItemClickedListener : OnItemClickedListener? = null

    inner class ViewHolder(val binding: CategoryRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(categoryModuleItem: CategoryModule.CategoryModuleItem){
            Glide.with(binding.thumbImg).load(categoryModuleItem.previewPhotos!![2]?.urls?.small.toString()).into(binding.thumbImg)
            binding.titleCollection.text = categoryModuleItem.title.toString()
            binding.totalPhotosCollection.text = categoryModuleItem.totalPhotos.toString()+" "+"Wallpapers"

            binding.root.setOnClickListener {
                onItemClickedListener?.onItemClicked(categoryModuleItem.slug!!)
            }
        }
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<CategoryModule.CategoryModuleItem>(){
            override fun areItemsTheSame(
                oldItem: CategoryModule.CategoryModuleItem,
                newItem: CategoryModule.CategoryModuleItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CategoryModule.CategoryModuleItem,
                newItem: CategoryModule.CategoryModuleItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun setOnItemClickedListener(listener: OnItemClickedListener){
        this.onItemClickedListener = listener
    }
}

interface OnItemClickedListener{
    fun onItemClicked(slug : String)
}