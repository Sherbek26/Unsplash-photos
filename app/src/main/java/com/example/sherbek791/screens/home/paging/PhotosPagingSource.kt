package com.example.sherbek791.screens.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.home.module.PhotosModule

class PhotosPagingSource (val apiService: ApiService,private val orderBy : String) : PagingSource<Int, PhotosModule.PhotosModuleItem>(){


    override fun getRefreshKey(state: PagingState<Int, PhotosModule.PhotosModuleItem>): Int? {
        if (state.anchorPosition != null){
            val anchor = state.closestPageToPosition(state.anchorPosition!!)
            if (anchor?.prevKey != null){
                return anchor.prevKey?.plus(1)
            }else if (anchor?.nextKey != null){
                return anchor.nextKey?.minus(1)
            }
        }else {
            return null
        }

        return state.anchorPosition.let {
            state.closestPageToPosition(it!!)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosModule.PhotosModuleItem> {
        return try {
            val pageId = params.key ?: 1
            val response = apiService.getPhotos("XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo",pageId,orderBy)
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