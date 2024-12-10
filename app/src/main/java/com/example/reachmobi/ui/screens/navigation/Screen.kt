package com.example.reachmobi.ui.screens.navigation

sealed class Screen(val route: String) {
    object NewsSearchScreen : Screen("NewsSearchScreen")
    object ArticleDetailsScreen: Screen("ArticleDetailsScreen")
}
