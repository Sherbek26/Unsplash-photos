package com.example.sherbek791.screens.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentFavouriteBinding
import com.example.sherbek791.screens.favourite.adapter.FavouriteImagesAdapter
import com.example.sherbek791.screens.favourite.viewModel.FavouriteViewModel
import com.example.sherbek791.screens.home.adapter.ItemOffsetDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var _binding : FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FavouriteViewModel by viewModels()
    private lateinit var favouriteImagesAdapter: FavouriteImagesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainActivity).setBottomNavVisibility(View.VISIBLE)
        (activity as MainActivity).supportActionBar?.show()

        val searchBtn = (activity as MainActivity).returnSearch()
        searchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_favouriteFragment_to_searchFragment)
        }

//            viewModel.getFromDatabase().observe(viewLifecycleOwner) {
//                favouriteImagesAdapter = FavouriteImagesAdapter(it)
//                val layoutManager = GridLayoutManager(requireContext(),2)
//                val itemDecoration = ItemOffsetDecoration(requireContext(),R.dimen.item_off_set)
//                binding.recyclerView.layoutManager = layoutManager
//                binding.recyclerView.addItemDecoration(itemDecoration)
//                binding.recyclerView.adapter = favouriteImagesAdapter
//            }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}