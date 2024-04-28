package com.example.sherbek791.screens.owner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sherbek791.databinding.ProfileRvPhotosBinding
import com.example.sherbek791.databinding.SearchPhotosRvAdapterBinding
import com.example.sherbek791.screens.owner.module.ProfileModule
import com.example.sherbek791.screens.owner.module.ProfilePicturesModule

class ProfilePhotosAdapter : PagingDataAdapter<ProfilePicturesModule.ProfilePicturesModuleItem,ProfilePhotosAdapter.ViewHolder>(
    diffUtil){

    var listener : OnImageClickedListener? = null

    inner class ViewHolder(val binding : ProfileRvPhotosBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(profilePicturesModuleItem: ProfilePicturesModule.ProfilePicturesModuleItem){
            Glide.with(binding.image).load(profilePicturesModuleItem.urls?.small).into(binding.image)

            binding.image.setOnClickListener{
                listener?.onImageClicked(profilePicturesModuleItem.urls?.full.toString(),profilePicturesModuleItem.updatedAt.toString())
            }
        }
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<ProfilePicturesModule.ProfilePicturesModuleItem>() {
                override fun areItemsTheSame(
                    oldItem: ProfilePicturesModule.ProfilePicturesModuleItem,
                    newItem: ProfilePicturesModule.ProfilePicturesModuleItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ProfilePicturesModule.ProfilePicturesModuleItem,
                    newItem: ProfilePicturesModule.ProfilePicturesModuleItem
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
        return ViewHolder(ProfileRvPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun setOnImageClickedListener(listener: OnImageClickedListener){
        this.listener = listener
    }
}

interface OnImageClickedListener{
    fun onImageClicked(imageId : String,updatedAt : String)
}