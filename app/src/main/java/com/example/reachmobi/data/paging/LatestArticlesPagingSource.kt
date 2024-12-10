package com.example.reachmobi.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reachmobi.data.model.Article
import com.example.reachmobi.data.model.toArticle
import com.example.reachmobi.network.NewsService
import okio.IOException
import javax.inject.Inject


class LatestArticlesPagingSource @Inject constructor (
    private val newsApiService: NewsService,
): PagingSource<Int, Article>() {

    companion object {
        const val TAG = "LatestArticlesPagingSource"
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = newsApiService.getNews(page = page,  pageSize = pageSize)

            if (response.isSuccessful) {
                val articles = response.body()?.articles.orEmpty()
                val mappedArticles = articles.filter { it.title != "[Removed]" }
                    .map { it.toArticle() }

                LoadResult.Page(
                    data = mappedArticles,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (articles.isEmpty()) null else page.plus(1),
                )

            } else {

                // internal use only, would create a custom logger to map for us
                val error =  if(response.code() == 429){
                    "Too many requests. Api limit reached"
                } else {
                    response.message().toString()
                }
                Log.e(TAG, error)
                LoadResult.Error(Exception(response.message().toString()))
            }

        } catch (e: IOException) {
            Log.e(TAG, e.message.toString())
            LoadResult.Error(e)
        }
    }
}