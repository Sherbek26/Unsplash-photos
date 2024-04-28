package com.example.sherbek791.screens.insideCategory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.insideCategory.module.TopicModule
import com.example.sherbek791.screens.insideCategory.paging.TopicPhotosPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TopicRepository @Inject constructor(val apiService: ApiService) {

    private val topicList = MutableLiveData<TopicModule>()

    fun getTopic(slug : String){
        apiService.getTopic(slug,"XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo").enqueue(object : Callback<TopicModule>{
            override fun onResponse(call: Call<TopicModule>, response: Response<TopicModule>) {
                if (response.isSuccessful)
                    topicList.postValue(response.body())
            }

            override fun onFailure(call: Call<TopicModule>, t: Throwable) {
                t.message
            }
        })
    }

    fun getTopicList() : LiveData<TopicModule>{
        return topicList
    }

    fun getTopicPhotos(slug: String) = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {
            TopicPhotosPagingSource(apiService,slug)
        }
    ).liveData

}