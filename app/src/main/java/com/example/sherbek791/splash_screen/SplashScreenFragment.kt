package com.example.sherbek791.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.navigation.fragment.findNavController
import com.example.dependancyinjection.utility.NetworkHelper
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).setBottomNavVisibility(View.GONE)
        (activity as? MainActivity)?.supportActionBar?.hide()

        binding.gettingStarted.setOnClickListener {
            if (NetworkHelper(requireContext()).isNetworkConnected()) {
                Toast.makeText(requireContext(), "net is have", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_offlineModeFragment)
            }
        }
    }

}