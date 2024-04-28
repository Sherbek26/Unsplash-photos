package com.example.sherbek791.screens.home.repository

import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument.Page
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.database.AppDatabase
import com.example.sherbek791.database.DatabaseDao
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.screens.home.paging.PhotosPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(val apiService: ApiService, val databaseDao: DatabaseDao) {


    fun photo(orderBy : String) = Pager(
        config = PagingConfig(20, enablePlaceholders = true),
        pagingSourceFactory = {
            PhotosPagingSource(apiService, orderBy)
        }
    ).liveData
}