package com.example.sherbek791.screens.owner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.owner.module.ProfileModule
import com.example.sherbek791.screens.owner.module.ProfilePicturesModule
import com.example.sherbek791.screens.owner.picturesPaging.ProfilePicturesPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(val apiService: ApiService) {

    private val profile = MutableLiveData<ProfileModule>()

    fun getProfile(username : String){
        apiService.getProfile(username,"XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo").enqueue(object : Callback<ProfileModule>{
            override fun onResponse(call: Call<ProfileModule>, response: Response<ProfileModule>) {
                if (response.isSuccessful)
                    profile.postValue(response.body())
            }

            override fun onFailure(call: Call<ProfileModule>, t: Throwable) {
                t.message
            }
        })
    }
    fun getProfileLively() : LiveData<ProfileModule>{
        return profile
    }

    fun getProfilePictures(username: String) = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = {
            ProfilePicturesPagingSource(apiService,username,"XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo")
        }
    ).liveData
}