package com.example.sherbek791.screens.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.sherbek791.R
import com.example.sherbek791.databinding.HomePhotosRecyclerViewBinding
import com.example.sherbek791.screens.home.module.PhotosModule
import javax.inject.Inject

class PhotosPagingAdapter : PagingDataAdapter<PhotosModule.PhotosModuleItem,PhotosPagingAdapter.ViewHolder>(
    diffUtil){

    private lateinit var onProfileClickedListener : OnProfileClickedListener
    private lateinit var onImageClickedListener: OnImageClickedListener

    inner class ViewHolder(val binding : HomePhotosRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photosModuleItem: PhotosModule.PhotosModuleItem) {

            Glide.with(binding.image).load(
                photosModuleItem.urls?.small.toString()
            ).into(binding.image)

            Glide
                .with(binding.profileImg)
                .load(
                photosModuleItem.user?.profileImage?.large.toString()
            ).into(binding.profileImg)

            if (photosModuleItem.user?.lastName.toString()=="null") {
                binding.ownerName.text =
                    photosModuleItem.user?.firstName.toString()
            } else {
                binding.ownerName.text =
                    photosModuleItem.user?.firstName.toString() + " " + photosModuleItem.user?.lastName.toString()
            }

            binding.profileImg.setOnClickListener {
                val username = photosModuleItem.user?.username
                if (username != null) {
                    onProfileClickedListener.onProfileClicked(username)
                }
            }

            binding.image.setOnClickListener {
                onImageClickedListener.onImageClicked(photosModuleItem.urls?.full.toString(),photosModuleItem.updatedAt.toString())
            }
        }
    }
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<PhotosModule.PhotosModuleItem>(){
            override fun areItemsTheSame(
                oldItem: PhotosModule.PhotosModuleItem,
                newItem: PhotosModule.PhotosModuleItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PhotosModule.PhotosModuleItem,
                newItem: PhotosModule.PhotosModuleItem
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
        return ViewHolder(HomePhotosRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun setOnProfileClickedListener(listener: OnProfileClickedListener){
        onProfileClickedListener = listener
    }
    fun setOnImageClickedListener(listener : OnImageClickedListener){
        this.onImageClickedListener = listener
    }

}

interface OnLikeClickedListener{
    fun onLikeClicked(isLiked : Boolean,imageUrl : String, username : String, profileSlug : String, profileImgUrl : String,firstname : String,lastname: String)
}

interface OnProfileClickedListener{
    fun onProfileClicked(username : String)
}
interface OnImageClickedListener{
    fun onImageClicked(imageId : String,updatedAt : String)
}
