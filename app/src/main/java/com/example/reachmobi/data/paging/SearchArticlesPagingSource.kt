package com.example.reachmobi.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reachmobi.data.model.Article
import com.example.reachmobi.data.model.toArticle
import com.example.reachmobi.network.NewsService
import java.io.IOException

class SearchArticlesPagingSource(
    private val newsService: NewsService,
    private val query: String
) : PagingSource<Int, Article>() {

    companion object {
        const val TAG = "SearchArticlesPagingSource"
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val response = newsService.searchNews(query, page = position, pageSize = pageSize)

            if (response.isSuccessful) {
                val articles = response.body()?.articles.orEmpty()
                val mappedArticles = articles.filter { it.title != "[Removed]" }
                    .map { it.toArticle() }

                LoadResult.Page(
                    data = mappedArticles,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (articles.isEmpty()) null else position + 1
                )
            } else {
                // internal use only, would create a custom logger to map for us
                val error =  if(response.code() == 429){
                    "Too many requests. Api limit reached"
                } else {
                    response.message().toString()
                }
                Log.e(LatestArticlesPagingSource.TAG, error)
                LoadResult.Error(Exception(error))
            }
        } catch (e: IOException) {
            Log.e(TAG, e.message.toString())
            LoadResult.Error(e)
        }
    }
}
