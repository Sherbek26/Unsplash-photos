package com.example.sherbek791.screens.owner.picturesPaging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.owner.module.ProfilePicturesModule
import java.lang.Exception

class ProfilePicturesPagingSource(val apiService: ApiService,val username : String,val clientId : String) : PagingSource<Int,ProfilePicturesModule.ProfilePicturesModuleItem>() {
    override fun getRefreshKey(state: PagingState<Int, ProfilePicturesModule.ProfilePicturesModuleItem>): Int? {
        if (state.anchorPosition != null){
            val anchor = state.closestPageToPosition(state.anchorPosition!!)
            if (anchor?.prevKey != null){
                anchor.prevKey!!.plus(1)
            }else if (anchor?.nextKey != null){
                anchor.nextKey!!.minus(1)
            }
        }else{
            return null
        }
        return state.anchorPosition.let {
            state.closestPageToPosition(it!!)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProfilePicturesModule.ProfilePicturesModuleItem> {
        return try {
            val pageId = params.key ?: 1
            val response = apiService.getProfilePictures(username,clientId,pageId)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else pageId+1
            )
        }catch (e : Exception){
            LoadResult.Error(Throwable(e.message))
        }
    }
}