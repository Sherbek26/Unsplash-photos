package com.example.sherbek791.screens.favourite.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sherbek791.database.entities.DatabaseEntity
import com.example.sherbek791.screens.favourite.repository.FavouriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(val repository: FavouriteRepository) : ViewModel(){
//    private val listDatabaseEntity = MutableLiveData<List<DatabaseEntity>>()
//    fun getFromDatabase() : LiveData<List<DatabaseEntity>>{
//        viewModelScope.launch {
//            listDatabaseEntity.value = repository.getDatabaseEntity()
//        }
//        return listDatabaseEntity
//    }
}