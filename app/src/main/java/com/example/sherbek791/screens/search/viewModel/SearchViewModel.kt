package com.example.sherbek791.screens.search.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sherbek791.screens.search.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository : SearchRepository) : ViewModel(){

    val query = MutableLiveData<String>()

    val searchPhotos = query.switchMap {
        repository.searchPhotos(it).cachedIn(viewModelScope)
    }

    fun setQuery(query : String){
        this.query.value = query
    }

}