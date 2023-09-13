package com.example.animetejc.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.animetejc.data.api.RickAndMortyApiService
import com.example.animetejc.data.response.Result
import com.google.gson.GsonBuilder

class CharacterPagingSource : PagingSource<Int, Result>() {
    private val TAG = this::class.simpleName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageNumber = params.key ?: 1
        return try {
            val response = RickAndMortyApiService.service.getCharacters(pageNumber)
            Log.d(
                TAG, "response info = " +
                        GsonBuilder().setPrettyPrinting().create().toJson(response.info)
            )
            if (response.results.isEmpty()) {
                throw IllegalStateException("response is null")
            }
            LoadResult.Page(
                data = response.results,
                prevKey = if (pageNumber == 1) null else (pageNumber - 1),
                nextKey = pageNumber + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}