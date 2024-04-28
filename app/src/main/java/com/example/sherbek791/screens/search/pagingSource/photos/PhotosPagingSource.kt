package com.example.sherbek791.screens.search.pagingSource.photos

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.search.modules.photos.ResultsPhotosItem
import com.example.sherbek791.screens.search.modules.photos.SearchPhotosModule

class PhotosPagingSource(val apiService: ApiService,private val query : String) : PagingSource<Int,ResultsPhotosItem>() {
    override fun getRefreshKey(state: PagingState<Int, ResultsPhotosItem>): Int? {
        if (state.anchorPosition != null){
            val anchor = state.closestPageToPosition(state.anchorPosition!!)
            if (anchor?.prevKey != null){
                anchor.prevKey?.plus(1)
            }else if (anchor?.nextKey != null){
                anchor.nextKey?.minus(1)
            }
        }else{
            return null
        }
        return state.anchorPosition.let {
            state.closestPageToPosition(it!!)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsPhotosItem> {
        return try {
            val pageId = params.key ?: 1
            val response = apiService.getSearchPhotos("XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo",pageId,query)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (pageId == response.totalPages) null else pageId+1
            )
        }catch (e : Exception){
            LoadResult.Error(Throwable(e.message))
        }
    }
}