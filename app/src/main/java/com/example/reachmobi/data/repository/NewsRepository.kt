package com.example.reachmobi.data.repository

import androidx.paging.PagingData
import com.example.reachmobi.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getSearchResults(query: String): Flow<PagingData<Article>>
    fun getLatestArticles(): Flow<PagingData<Article>>
}