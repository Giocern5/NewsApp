package com.example.reachmobi.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.reachmobi.data.paging.LatestArticlesPagingSource
import com.example.reachmobi.data.paging.SearchArticlesPagingSource
import com.example.reachmobi.data.model.*
import com.example.reachmobi.network.NewsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val pageSize = 20

class NewsRepositoryImpl @Inject constructor(private val newsService: NewsService): NewsRepository {

    companion object {
         const val TAG = "NewsRepositoryImpl"
    }

    override fun getSearchResults(query: String): Flow<PagingData<Article>> {
        Log.e(TAG, "Fetching search results")
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { SearchArticlesPagingSource(newsService, query) }
        ).flow
    }

    override fun getLatestArticles(): Flow<PagingData<Article>> {
        Log.e(TAG, "Fetching latest articles")
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { LatestArticlesPagingSource(newsService) }
        ).flow
    }
}