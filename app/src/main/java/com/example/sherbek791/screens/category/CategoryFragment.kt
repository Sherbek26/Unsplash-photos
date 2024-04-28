package com.example.sherbek791.screens.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dependancyinjection.utility.NetworkHelper
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentCategoryfragmentBinding
import com.example.sherbek791.screens.category.adapter.CategoryPagingAdapter
import com.example.sherbek791.screens.category.adapter.OnItemClickedListener
import com.example.sherbek791.screens.category.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding : FragmentCategoryfragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel : CategoryViewModel by viewModels()
    private val categoryPagingAdapter = CategoryPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setBottomNavVisibility(View.VISIBLE)
        (activity as MainActivity).supportActionBar?.show()

        val searchBtn = (activity as MainActivity).returnSearch()
        searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_searchFragment)
        }

        binding.recyclerCatogry.adapter = categoryPagingAdapter

        if (NetworkHelper(requireContext()).isNetworkConnected()) {
            lifecycleScope.launch {
                viewModel.category.observe(requireActivity()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        categoryPagingAdapter.submitData(it)
                    }
                }
            }
        }

        categoryPagingAdapter.setOnItemClickedListener(object : OnItemClickedListener{
            override fun onItemClicked(slug: String) {
                val action = CategoryFragmentDirections.actionCategoryFragmentToInsideCategoryFragment(slug)
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