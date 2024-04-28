package com.example.sherbek791.screens.owner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sherbek791.databinding.ProfileInterestsRvBinding
import com.example.sherbek791.screens.owner.module.CustomItem

class ProfileInterestsRvAdapter(val list: List<CustomItem>) : RecyclerView.Adapter<ProfileInterestsRvAdapter.ViewHolder>(){

    inner class ViewHolder(val binding : ProfileInterestsRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(customItem: CustomItem){
            binding.interestText.text = customItem.title.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProfileInterestsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}