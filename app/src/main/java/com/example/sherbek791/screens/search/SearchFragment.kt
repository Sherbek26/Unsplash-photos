package com.example.sherbek791.screens.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TableLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentSearchBinding
import com.example.sherbek791.screens.home.HomeFragmentDirections
import com.example.sherbek791.screens.home.adapter.ItemOffsetDecoration
import com.example.sherbek791.screens.home.adapter.OnImageClickedListener
import com.example.sherbek791.screens.home.adapter.OnProfileClickedListener
import com.example.sherbek791.screens.search.adapter.SearchPhotosAdapter
import com.example.sherbek791.screens.search.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(){

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel : SearchViewModel by viewModels()
    private val searchPhotosAdapter = SearchPhotosAdapter()
    private var defaultQuery =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainActivity).setBottomNavVisibility(View.GONE)
        (activity as? MainActivity)?.supportActionBar?.hide()
        defaultQuery = "office"
        viewModel.setQuery(defaultQuery)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2);
        val itemDecoration = ItemOffsetDecoration(requireContext(), R.dimen.item_off_set);
        binding.recyclerView.addItemDecoration(itemDecoration);
        binding.recyclerView.adapter = searchPhotosAdapter

        lifecycleScope.launch {
            viewModel.searchPhotos.observe(viewLifecycleOwner){
                CoroutineScope(Dispatchers.IO).launch {
                    searchPhotosAdapter.submitData(it)
                }
            }
        }


        searchPhotosAdapter.setOnProfileClickedListener(object : OnProfileClickedListener {
            override fun onProfileClicked(username: String) {
                val action = SearchFragmentDirections.actionSearchFragmentToPhotoOwnerFragment(username)
                findNavController().navigate(action)
            }
        })


        searchPhotosAdapter.setOnImageClickedListener(object : OnImageClickedListener {
            override fun onImageClicked(imageId: String,updatedAt : String) {
                val action = SearchFragmentDirections.actionSearchFragmentToImageFragment(imageId,updatedAt)
                findNavController().navigate(action)
            }

        })
        binding.searchView.setOnCloseListener {
            viewModel.setQuery(defaultQuery)
            true
        }
        setHasOptionsMenu(true)

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.setQuery(query)
                    defaultQuery = query
                    binding.searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

}
