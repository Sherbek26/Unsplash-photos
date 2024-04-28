package com.example.sherbek791.screens.insideCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentInsideCategoryBinding
import com.example.sherbek791.screens.home.adapter.ItemOffsetDecoration
import com.example.sherbek791.screens.insideCategory.adapter.OnImageClickedListener
import com.example.sherbek791.screens.insideCategory.adapter.TopicPhotosAdapter
import com.example.sherbek791.screens.insideCategory.viewModel.TopicViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.abs


@AndroidEntryPoint
class InsideCategoryFragment : Fragment(),AppBarLayout.OnOffsetChangedListener {

    private var _binding : FragmentInsideCategoryBinding? = null
    private val binding get() = _binding!!
    private val args : InsideCategoryFragmentArgs by navArgs()
    private val viewModel : TopicViewModel by viewModels()
    private val topicPhotosAdapter = TopicPhotosAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInsideCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainActivity).setBottomNavVisibility(View.GONE)
        (activity as MainActivity).supportActionBar?.hide()

        viewModel.slug.value = args.categorySlug
        viewModel.getTopic().observe(requireActivity()){
            binding.slug.text = it.title.toString()
            binding.topicDescription.text = it.description.toString()
            Glide.with(binding.topicCoverImg).load(it.previewPhotos!![3]?.urls?.small.toString()).into(binding.topicCoverImg)

        }

        binding.recyclerTopicPhotos.layoutManager = GridLayoutManager(requireContext(), 2);
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.item_off_set);
        binding.recyclerTopicPhotos.addItemDecoration(itemDecoration);
        binding.recyclerTopicPhotos.adapter = topicPhotosAdapter

        viewModel.slug.observe(requireActivity()){
            lifecycleScope.launch {
                viewModel.topicPhotos.observe(requireActivity()){
                    CoroutineScope(Dispatchers.IO).launch {
                        topicPhotosAdapter.submitData(it)
                    }
                }
            }
        }

        binding.appBarLayout.addOnOffsetChangedListener(this)

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        topicPhotosAdapter.setOnImageClickedListener(object : OnImageClickedListener{
            override fun onImageClicked(imageId: String,updatedAt : String) {
                val action = InsideCategoryFragmentDirections.actionInsideCategoryFragmentToImageFragment(imageId,updatedAt)
                findNavController().navigate(action)
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val isExpanded = verticalOffset == 0
        val isCollapsed = abs(verticalOffset) >= binding.appBarLayout.totalScrollRange

        if (isExpanded){
            binding.backBtn.visibility = View.INVISIBLE
        }
        if (isCollapsed){
            binding.backBtn.visibility = View.VISIBLE
        }
    }
}