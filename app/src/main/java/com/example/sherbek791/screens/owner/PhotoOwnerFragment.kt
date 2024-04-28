package com.example.sherbek791.screens.owner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentPhotoOwnerBinding
import com.example.sherbek791.screens.home.adapter.ItemOffsetDecoration
import com.example.sherbek791.screens.owner.adapter.OnImageClickedListener
import com.example.sherbek791.screens.owner.adapter.ProfileInterestsRvAdapter
import com.example.sherbek791.screens.owner.adapter.ProfilePhotosAdapter
import com.example.sherbek791.screens.owner.module.CustomItem
import com.example.sherbek791.screens.owner.module.ProfileModule
import com.example.sherbek791.screens.owner.viewModel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoOwnerFragment : Fragment() {

    private var _binding : FragmentPhotoOwnerBinding? = null
    private val binding get() = _binding!!
    private val args : PhotoOwnerFragmentArgs by navArgs()
    private val viewModel : ProfileViewModel by viewModels()
    private lateinit var profileInterestsRvAdapter: ProfileInterestsRvAdapter
    private val profilePhotosAdapter = ProfilePhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPhotoOwnerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainActivity).setBottomNavVisibility(View.GONE)
        (activity as MainActivity).supportActionBar?.hide()

        viewModel.userName.value = args.username
        viewModel.getProfile().observe(requireActivity()){
            Glide.with(binding.profileImg).load(it.profileImage?.large).into(binding.profileImg)

            if (it.name.toString() != "null") {
                binding.name.text = it.name.toString()
            }else{
                binding.name.text = "////"
            }
            if (it.bio.toString() != "null") {
                binding.bio.text = it.bio.toString()
            }else{
                binding.bio.text = "////"
            }

            if(it.location.toString() != "null") {
                binding.city.text = it.location.toString()
            }else{
                binding.city.text = "none"
            }


            if (it.totalLikes!! <= 999){
                binding.like.text = it.totalLikes.toString() + " "+"likes"
            }else{
                val number = it.totalLikes / 1000
                binding.like.text = "$number likes"
            }
            binding.numGallery.text = it.totalPhotos.toString()

            if (it.tags?.custom != null&& it.tags?.custom.isNotEmpty()) {
                binding.interest.visibility= View.VISIBLE
                profileInterestsRvAdapter = ProfileInterestsRvAdapter(it.tags.custom as List<CustomItem>)
                binding.recyclerInterests.adapter = profileInterestsRvAdapter
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.recyclerPhotos.layoutManager = GridLayoutManager(context, 3);
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.item_profile_set);
        binding.recyclerPhotos.addItemDecoration(itemDecoration);
        binding.recyclerPhotos.adapter = profilePhotosAdapter

        viewModel.userName.observe(requireActivity()){
            lifecycleScope.launch {
                viewModel.profilePictures.observe(requireActivity()){
                    CoroutineScope(Dispatchers.IO).launch {
                        profilePhotosAdapter.submitData(it)
                    }
                }
            }
        }

        profilePhotosAdapter.setOnImageClickedListener(object : OnImageClickedListener{
            override fun onImageClicked(imageId: String,updatedAt : String) {
                val action = PhotoOwnerFragmentDirections.actionPhotoOwnerFragmentToImageFragment(imageId,updatedAt)
                findNavController().navigate(action)
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}