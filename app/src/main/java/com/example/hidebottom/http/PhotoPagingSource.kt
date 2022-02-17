package com.example.hidebottom.http

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hidebottom.bean.Hit

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/10/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
private const val GITHUB_STARTING_PAGE_INDEX = 1

class PhotoPagingSource() :
    PagingSource<Int, Hit>() {

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        Log.d("PAGING3", "key")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        Log.d("PAGING3", queryKey)
        return try {
            val page = params.key ?: GITHUB_STARTING_PAGE_INDEX     // set page 1 as default
            val response = PhotoRepository.getInstance(PhotoNetwork.getInstance())
                .getPictureSouce(key, queryKey, 50, page, true)
            val nextPage = if (!response.hits.isNullOrEmpty()) page + 1 else null
            val prevPage = if (page > 1) page - 1 else null
            LoadResult.Page(data = response.hits, prevKey = prevPage, nextKey = nextPage)
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    private var key = "18138703-f8467438c77adc35dabe81fca"
    private var queryKey =
        arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal", "yellow", "yellow+flower").random()
}