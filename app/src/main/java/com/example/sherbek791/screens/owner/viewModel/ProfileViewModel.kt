package com.example.sherbek791.screens.owner.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sherbek791.screens.owner.module.ProfileModule
import com.example.sherbek791.screens.owner.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repository: ProfileRepository) : ViewModel() {
    val userName = MutableLiveData<String>()

    fun getProfile() : LiveData<ProfileModule>{
        repository.getProfile(userName.value!!)
        return repository.getProfileLively()
    }

    val profilePictures = userName.switchMap {
        repository.getProfilePictures(it).cachedIn(viewModelScope)
    }
}