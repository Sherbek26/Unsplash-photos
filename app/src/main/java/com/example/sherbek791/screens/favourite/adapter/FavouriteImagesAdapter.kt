package com.example.sherbek791.screens.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.databinding.ImageDbRvBinding

class FavouriteImagesAdapter(val list: List<DatabaseEntity>) :  RecyclerView.Adapter<FavouriteImagesAdapter.ViewHolder>(){

    inner class ViewHolder(val binding : ImageDbRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(databaseEntity: DatabaseEntity){
            binding.image.setImageBitmap(databaseEntity.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ImageDbRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}