package com.example.sherbek791.screens.image.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.provider.ContactsContract.Data
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.sherbek791.R
import com.example.sherbek791.database.AppDatabase
import com.example.sherbek791.database.DatabaseDao
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.screens.image.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class ImageViewModel @Inject constructor(val databaseDao: DatabaseDao) : ViewModel() {

    suspend fun saveToDatabase(databaseEntity: DatabaseEntity){
        databaseDao.cacheImages(databaseEntity)
    }

}