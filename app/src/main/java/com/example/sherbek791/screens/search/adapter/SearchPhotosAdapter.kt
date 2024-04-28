package com.example.sherbek791.screens.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sherbek791.databinding.HomePhotosRecyclerViewBinding
import com.example.sherbek791.databinding.SearchPhotosRvAdapterBinding
import com.example.sherbek791.screens.home.adapter.OnImageClickedListener
import com.example.sherbek791.screens.home.adapter.OnLikeClickedListener
import com.example.sherbek791.screens.home.adapter.OnProfileClickedListener
import com.example.sherbek791.screens.search.modules.photos.ResultsPhotosItem

class SearchPhotosAdapter : PagingDataAdapter<ResultsPhotosItem,SearchPhotosAdapter.ViewHolder>(diffUtil){

    private lateinit var onProfileClickedListener : OnProfileClickedListener
    private lateinit var onImageClickedListener: OnImageClickedListener

    inner class ViewHolder(val binding : HomePhotosRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(resultsPhotosItem: ResultsPhotosItem){
            Glide.with(binding.image).load(
                resultsPhotosItem.urls?.small.toString()
            ).into(binding.image)

            Glide
                .with(binding.profileImg)
                .load(
                    resultsPhotosItem.user?.profileImage?.large.toString()
                ).into(binding.profileImg)

            if (resultsPhotosItem.user?.lastName.toString()=="null") {
                binding.ownerName.text =
                    resultsPhotosItem.user?.firstName.toString()
            } else {
                binding.ownerName.text =
                    resultsPhotosItem.user?.firstName.toString() + " " + resultsPhotosItem.user?.lastName.toString()
            }

            binding.profileImg.setOnClickListener {
                val username = resultsPhotosItem.user?.username
                if (username != null) {
                    onProfileClickedListener.onProfileClicked(username)
                }
            }

            binding.image.setOnClickListener {
                onImageClickedListener.onImageClicked(resultsPhotosItem.urls?.full.toString(),resultsPhotosItem.updatedAt.toString())
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResultsPhotosItem>() {
            override fun areItemsTheSame(
                oldItem: ResultsPhotosItem,
                newItem: ResultsPhotosItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResultsPhotosItem,
                newItem: ResultsPhotosItem
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
interface OnProfileClickedListener{
    fun onProfileClicked(username : String)
}
interface OnImageClickedListener{
    fun onImageClicked(imageId : String,updatedAt : String)
}