package com.example.reachmobi.ui.screens.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reachmobi.ui.screens.details.ArticleDetailsScreen
import com.example.reachmobi.ui.screens.search.NewsSearchScreen
import com.example.reachmobi.ui.screens.viewmodel.NewsViewModel

@Composable
fun NewsApp() {
    val navController = rememberNavController()
    val viewModel =  hiltViewModel<NewsViewModel>()

    NavHost(navController = navController, startDestination = Screen.NewsSearchScreen.route,) {
        composable(
            route = Screen.NewsSearchScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(500)
                )
            }) {
                NewsSearchScreen(navController, viewModel)
        }

        composable( route = Screen.ArticleDetailsScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(400)
                )
            }) {
                ArticleDetailsScreen(viewModel)
        }
    }

}

