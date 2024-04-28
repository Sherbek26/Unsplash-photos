package com.example.sherbek791.screens.category.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sherbek791.database.AppDatabase
import com.example.sherbek791.screens.category.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val repository: CategoryRepository,
    ) : ViewModel() {
    val category = repository.category().cachedIn(viewModelScope)
}