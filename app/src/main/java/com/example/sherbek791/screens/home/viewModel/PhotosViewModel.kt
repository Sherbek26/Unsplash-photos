package com.example.sherbek791.screens.home.viewModel

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.screens.home.repository.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class PhotosViewModel @Inject constructor(val repository: PhotosRepository) : ViewModel() {
    var orderBy = MutableLiveData<String>()
    var recyclerViewState: Parcelable? = null


    val photos = orderBy.switchMap {
        repository.photo(it).cachedIn(viewModelScope)
    }

}