package com.example.sherbek791.screens.insideCategory.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.sherbek791.screens.insideCategory.module.TopicModule
import com.example.sherbek791.screens.insideCategory.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(val repository: TopicRepository) : ViewModel() {

    val slug = MutableLiveData<String>()

    fun getTopic() : LiveData<TopicModule>{
        repository.getTopic(slug.value!!)
        return repository.getTopicList()
    }

    val topicPhotos = slug.switchMap {
        repository.getTopicPhotos(it).cachedIn(viewModelScope)
    }

}