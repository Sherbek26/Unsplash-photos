package com.example.sherbek791.screens.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.databinding.FragmentHomeBinding
import com.example.sherbek791.screens.home.adapter.ItemOffsetDecoration
import com.example.sherbek791.screens.home.adapter.OnImageClickedListener
import com.example.sherbek791.screens.home.adapter.OnLikeClickedListener
import com.example.sherbek791.screens.home.adapter.OnProfileClickedListener
import com.example.sherbek791.screens.home.adapter.PhotosPagingAdapter
import com.example.sherbek791.screens.home.viewModel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(),PopupMenu.OnMenuItemClickListener {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : PhotosViewModel by viewModels()
    private  var photosPagingAdapter = PhotosPagingAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainActivity).setBottomNavVisibility(View.VISIBLE)
        (activity as? MainActivity)?.supportActionBar?.show()

        binding.recyclerPhotos.layoutManager = GridLayoutManager(context, 2);
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.item_off_set);
        binding.recyclerPhotos.addItemDecoration(itemDecoration);
        binding.recyclerPhotos.adapter = photosPagingAdapter

        binding.popupMenu.setOnClickListener {
            showPopUpMenu(it)
        }

        val searchBtn = (activity as MainActivity).returnSearch()
        searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        viewModel.orderBy.value = "latest"
        viewModel.orderBy.observe(requireActivity()){
            lifecycleScope.launch {
                viewModel.photos.observe(this@HomeFragment) {
                    CoroutineScope(Dispatchers.IO).launch {
                        photosPagingAdapter.submitData(it)
                    }
                }
            }
        }


        photosPagingAdapter.setOnProfileClickedListener(object : OnProfileClickedListener {
            override fun onProfileClicked(username: String) {
                val action = HomeFragmentDirections.actionHomeFragmentToPhotoOwnerFragment(username)
                findNavController().navigate(action)
            }
        })


        photosPagingAdapter.setOnImageClickedListener(object : OnImageClickedListener{
            override fun onImageClicked(imageId: String,updatedAt : String) {
                val action = HomeFragmentDirections.actionHomeFragmentToImageFragment(imageId,updatedAt)
                findNavController().navigate(action)
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }
    private fun showPopUpMenu(view: View){
        val popUpMenu = PopupMenu(requireContext(),view)
        popUpMenu.setOnMenuItemClickListener(this)
        popUpMenu.inflate(R.menu.popup_menu)
        popUpMenu.show()
    }

    override fun onMenuItemClick(menuItem: MenuItem): Boolean {
        binding.orderBy.text = menuItem.title.toString()
        if (menuItem.itemId == R.id.trending){
            viewModel.orderBy.value = "popular"
        }else{
            viewModel.orderBy.value = menuItem.title.toString().lowercase()
        }
        photosPagingAdapter.refresh()
        return true
    }



    fun loadImageAsBitmap(imageUrl: String, callback: (Bitmap?) -> Unit) {
        Glide.with(requireContext())
            .asBitmap()
            .load(imageUrl)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    // Callback with the loaded Bitmap
                    callback(resource)
                }
            })
    }
}