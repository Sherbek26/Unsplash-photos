package com.example.sherbek791.screens.category.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.category.paging.CategoryPagingSource
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    val apiService: ApiService,
    ) {
    fun category() = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = {
            CategoryPagingSource(apiService)
        }
    ).liveData
}