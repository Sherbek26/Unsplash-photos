package com.example.sherbek791.screens.image

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.sherbek791.MainActivity
import com.example.sherbek791.R
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.databinding.FragmentImageBinding
import com.example.sherbek791.screens.image.viewModel.ImageViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.makeramen.roundedimageview.RoundedDrawable.drawableToBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@AndroidEntryPoint
class ImageFragment : Fragment(){

    private var _binding : FragmentImageBinding? = null
    private val binding get() = _binding!!
    private val args : ImageFragmentArgs by navArgs()
    private var imageBitmap : Bitmap? = null
    private val viewModel : ImageViewModel by viewModels()
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private var isLiked : Boolean = false
    var databaseEntity : DatabaseEntity? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as MainActivity).setBottomNavVisibility(View.GONE)
        (activity as? MainActivity)?.supportActionBar?.hide()


        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.progressBar.visibility = View.VISIBLE

        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        val requestCode = 1



        binding.download.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(permission),
                    requestCode
                )
            } else {
                // Permission is already granted; you can proceed to download the image
                downloadImage(args.imageUrl)
            }
        }

        binding.apply.setOnClickListener {
            showBottomSheet()
        }

//        2021-03-02T16:50:00Z
        val updatedAt = args.updatedAt.substring(0,10)
        val tooltipText = "Uploaded:$updatedAt"

        binding.info.setOnClickListener{
            Toast.makeText(requireContext(), "To know about when this picture was uploaded, please press longly", Toast.LENGTH_SHORT).show()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.info.tooltipText = tooltipText
        }

        binding.like.setOnClickListener {
            isLiked = !isLiked
            CoroutineScope(Dispatchers.IO).launch {
                likeImage()
            }
        }


        CoroutineScope(Dispatchers.IO).launch {
            try {
                coroutineScope{
                    val deferred: Deferred<Bitmap> = async { loadImageAsBitmap(args.imageUrl)!! }
                    val bitmap = deferred.await()
                    Log.d("img",bitmap.toString())
                    databaseEntity = DatabaseEntity(0,bitmap)
                    binding.image.setImageBitmap(bitmap)
                }
                viewModel.saveToDatabase(databaseEntity!!)
            } catch (e: Exception) {
                // Handle any exceptions that might occur during image loading
                e.printStackTrace()
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.image.setImageBitmap(null)
    }

    fun downloadImage(imageUrl: String) {
        val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(imageUrl))

        // Create a unique file name based on the URL
        val fileName = "unique_file_name.jpg" // Change this to generate a unique file name

        // Get the public external pictures directory
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

        // Create a file Uri for the downloaded image
        val fileUri = Uri.fromFile(File(storageDir, fileName))

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setAllowedOverRoaming(true)
            .setTitle(fileName)
            .setDescription("Downloading an image from Wallpaper")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName)

        downloadManager.enqueue(request)
        Toast.makeText(requireContext(), "Image is being downloaded", Toast.LENGTH_SHORT).show()
    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted; proceed to download the image
                downloadImage(args.imageUrl)
            } else {
                // Permission denied; handle accordingly
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showBottomSheet(){
            val dialogBinding = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
            val dialog = BottomSheetDialog(requireActivity())

            dialog.setContentView(dialogBinding)
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            val lockScreen = dialog.findViewById<AppCompatButton>(R.id.lock_screen)
            val homeScreen = dialog.findViewById<AppCompatButton>(R.id.home_screen)
            val both = dialog.findViewById<AppCompatButton>(R.id.both_screen)

        lockScreen?.setOnClickListener {
            if (imageBitmap != null){
                ioScope.launch {
                    settingImageToLockScreen(imageBitmap!!)
                }
                Toast.makeText(requireContext(), "Image was set up to Lock Screen", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        homeScreen?.setOnClickListener {
            ioScope.launch {
                settingImageToHomeScreen(imageBitmap!!)
            }
            Toast.makeText(requireContext(), "Image was set up to Home Screen", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        both?.setOnClickListener {
            ioScope.launch {
                settingImageToHomeScreen(imageBitmap!!)
                settingImageToLockScreen(imageBitmap!!)
            }
            Toast.makeText(requireContext(), "Image was set up to Both Screens", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
            dialog.show()

    }

    suspend fun settingImageToLockScreen(bitmap : Bitmap) {
        withContext(Dispatchers.IO) {
            val wallpaperManager = WallpaperManager.getInstance(requireContext())
            try {
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    suspend fun settingImageToHomeScreen(bitmap : Bitmap) {
        withContext(Dispatchers.IO) {
            val wallpaperManager = WallpaperManager.getInstance(requireContext())
            try {
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onStop() {
        isLiked = false
        imageBitmap = null
        super.onStop()
    }
    suspend fun loadImageAsBitmap(imageUrl: String): Bitmap? = suspendCoroutine { continuation ->
        Glide.with(requireContext())
            .asBitmap()
            .load(imageUrl)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    // Callback with the loaded Bitmap
                    continuation.resume(resource)
                    binding.progressBar.visibility = View.GONE
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    // Handle loading failure
                    continuation.resume(null)
                }
            })
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    suspend fun likeImage(){
        if (isLiked){
            binding.like.setImageResource(R.drawable.heart_red)
            viewModel.saveToDatabase(databaseEntity!!)
        }else{
            binding.like.setImageResource(R.drawable.heart)
        }
    }
    override fun onDestroyView() {
        uiScope.cancel() // Cancel UI coroutine scope
        ioScope.cancel() // Cancel IO coroutine scope
        super.onDestroyView()
    }
}


