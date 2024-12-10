package com.example.reachmobi.ui.screens.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.reachmobi.analytics.AnalyticsLogger
import com.example.reachmobi.data.model.Article
import com.example.reachmobi.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import kotlinx.coroutines.flow.*

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repo: NewsRepository,
    private val analyticsLogger: AnalyticsLogger,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val TAG = "NewsViewModel"
        // used for user experience
        const val SAVESTATESEARCHKEY = "search_query"
    }

    private val _articleDetails = MutableStateFlow<Article?>(null)
    val articleDetails: StateFlow<Article?> get() = _articleDetails
    val searchString: StateFlow<String> = savedStateHandle.getStateFlow(SAVESTATESEARCHKEY, "")

    // errors are handled in compose screen
    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingFlow: Flow<PagingData<Article>> = searchString.flatMapLatest { query ->
        Log.e(TAG, "Fetching Articles")
         if (query.isEmpty()) {
             repo.getLatestArticles()
        } else {
             repo.getSearchResults(query)
        }
    }.cachedIn(viewModelScope)

    fun setSearchQuery(query: String) {
        analyticsLogger.logSearchQuery(query)
        savedStateHandle[SAVESTATESEARCHKEY] = query
    }

    fun setArticleDetails(article: Article) {
        analyticsLogger.logSelectedItem(article.source.name)
        _articleDetails.value = article
    }
}
