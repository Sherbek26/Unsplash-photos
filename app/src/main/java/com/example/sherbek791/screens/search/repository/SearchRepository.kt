package com.example.sherbek791.screens.search.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.search.pagingSource.photos.PhotosPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(val apiService: ApiService){
    fun searchPhotos(query : String) = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = {
            PhotosPagingSource(apiService,query)
        }
    ).liveData
}