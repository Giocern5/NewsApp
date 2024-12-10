package com.example.reachmobi.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.reachmobi.data.model.Article
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.reachmobi.ui.screens.navigation.Screen
import com.example.reachmobi.ui.screens.viewmodel.NewsViewModel
import com.example.reachmobi.utils.formatDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsSearchScreen(navController: NavHostController, viewModel: NewsViewModel) {

    // Saves search string when coming back from details page
    val searchString by viewModel.searchString.collectAsState()
    val articles = viewModel.pagingFlow.collectAsLazyPagingItems()
    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.setSearchQuery("")
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .pullRefresh(pullRefreshState)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(searchString) { query ->
                viewModel.setSearchQuery(query)
            }
            NewsList(
                articles = articles,
                isSearching = searchString.isNotEmpty(),
                onItemClick = { article ->
                    navController.navigate(Screen.ArticleDetailsScreen.route)
                    viewModel.setArticleDetails(article)
                },
                onRefresh = { loading ->
                    isRefreshing = loading
                }
            )
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun SearchBar(searchString: String,  onSearch: (String) -> Unit) {

    var searchBarText by remember { mutableStateOf(searchString) }

    // used for app refresh, clears text
    LaunchedEffect(searchString) {
        searchBarText = searchString
    }

    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)) {

        OutlinedTextField(value = searchBarText,
            onValueChange = { text ->
                searchBarText = text
            },
            modifier = Modifier
                .height(50.dp)
                .weight(1f),
            placeholder = {
                Text(text = "Search for an article...", color = Color.Gray)
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        Button(onClick = {
            onSearch(searchBarText)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.height(50.dp)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                modifier = Modifier.size(24.dp),
            )
        }
    }
}


@Composable
fun NewsList(articles: LazyPagingItems<Article>, isSearching: Boolean, onItemClick: (Article) -> Unit,
             onRefresh: (Boolean) -> Unit) {

    when (articles.loadState.refresh) {
        is LoadState.Error -> {
            onRefresh(false)
             CenterContainer("Ooops, something went wrong! Please check internet or try again")
        }
        is LoadState.Loading -> { onRefresh(true) }
        is LoadState.NotLoading -> {
            onRefresh(false)
            LazyColumn {
                if ( isSearching && articles.itemCount == 0   )
                    item { CenterContainer("No Results!") }

                items(articles.itemCount) { index ->
                    articles[index]?.let {
                        NewsItem(article = it, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}


@Composable
fun NewsItem(article: Article, onItemClick: (Article) -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(article) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(hoveredElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Source: ${article.source.name}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = formatDate(article.date),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun CenterContainer(message: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

