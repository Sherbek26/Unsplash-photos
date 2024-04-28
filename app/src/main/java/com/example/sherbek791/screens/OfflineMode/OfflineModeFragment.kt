package com.example.sherbek791.screens.OfflineMode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dependancyinjection.utility.NetworkHelper
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentOfflineModeBinding


class OfflineModeFragment : Fragment() {

    private var _binding : FragmentOfflineModeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOfflineModeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.retry.setOnClickListener {
            if (NetworkHelper(requireContext()).isNetworkConnected()){
                findNavController().navigate(R.id.action_offlineModeFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(), "Still,No Internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.favourite.setOnClickListener {
            findNavController().navigate(R.id.action_offlineModeFragment_to_favouriteFragment)
        }



        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}