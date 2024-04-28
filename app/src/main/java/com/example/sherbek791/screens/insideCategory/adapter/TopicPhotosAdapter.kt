package com.example.sherbek791.screens.insideCategory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sherbek791.databinding.TopicPhotosRvBinding
import com.example.sherbek791.screens.insideCategory.module.topicPhotos.TopicPhotosModule

class TopicPhotosAdapter : PagingDataAdapter<TopicPhotosModule.TopicPhotosModuleItem,TopicPhotosAdapter.ViewHolder>(
    diffUtil){

    var listener : OnImageClickedListener? = null

    inner class ViewHolder(val binding : TopicPhotosRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(topicPhotosModuleItem: TopicPhotosModule.TopicPhotosModuleItem){
            Glide.with(binding.image).load(topicPhotosModuleItem.urls?.small).into(binding.image)

            binding.image.setOnClickListener{
                listener?.onImageClicked(topicPhotosModuleItem.urls?.full.toString(),topicPhotosModuleItem.updatedAt.toString())
            }

        }
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<TopicPhotosModule.TopicPhotosModuleItem>() {
            override fun areItemsTheSame(
                oldItem: TopicPhotosModule.TopicPhotosModuleItem,
                newItem: TopicPhotosModule.TopicPhotosModuleItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TopicPhotosModule.TopicPhotosModuleItem,
                newItem: TopicPhotosModule.TopicPhotosModuleItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TopicPhotosRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    fun setOnImageClickedListener(listener: OnImageClickedListener){
        this.listener = listener
    }
}

interface OnImageClickedListener{
    fun onImageClicked(imageId : String,updatedAt : String)
}