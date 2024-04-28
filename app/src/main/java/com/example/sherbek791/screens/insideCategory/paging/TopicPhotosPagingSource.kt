package com.example.sherbek791.screens.insideCategory.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.insideCategory.module.topicPhotos.TopicPhotosModule

class TopicPhotosPagingSource(val apiService: ApiService,val slug : String) : PagingSource<Int, TopicPhotosModule.TopicPhotosModuleItem>(){
    override fun getRefreshKey(state: PagingState<Int, TopicPhotosModule.TopicPhotosModuleItem>): Int? {
//        if (state.anchorPosition != null){
//            val anchor = state.closestPageToPosition(state.anchorPosition!!)
//            if (anchor?.prevKey != null){
//                anchor.prevKey!!.plus(1)
//            }else if (anchor?.nextKey != null){
//                anchor.nextKey!!.minus(1)
//            }
//        }else{
//            return null
//        }
        return state.anchorPosition.let {
            state.closestPageToPosition(it!!)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicPhotosModule.TopicPhotosModuleItem> {
        return try {
            val pageId = params.key ?: 1
            val response = apiService.getTopicPhotos(slug,"XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo",pageId)
            LoadResult.Page(
                data = response,
                prevKey = if(pageId == 1) null else pageId-1,
                nextKey = if (response.isEmpty()) null else pageId+1
            )
        }catch (e : Exception){
            LoadResult.Error(Throwable(e.message))
        }
    }
}