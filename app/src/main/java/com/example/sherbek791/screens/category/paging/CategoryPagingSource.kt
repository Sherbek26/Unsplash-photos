package com.example.sherbek791.screens.category.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sherbek791.api.ApiService
import com.example.sherbek791.screens.category.modules.CategoryModule
import java.lang.Exception
import javax.inject.Inject

class CategoryPagingSource @Inject constructor(
    val apiService: ApiService,
) : PagingSource<Int,CategoryModule.CategoryModuleItem>(){
    override fun getRefreshKey(state: PagingState<Int, CategoryModule.CategoryModuleItem>): Int? {
        return state.anchorPosition.let {
            state.closestPageToPosition(it!!)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CategoryModule.CategoryModuleItem> {
        return try {
            val pageId = params.key ?: 1
            val clientId = "XHa6XE0bxndW7Z_h7h8bPQe6YqBzlzcCSfM_c1W-Ixo"
            val response = apiService.getCategories(clientId,pageId)
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