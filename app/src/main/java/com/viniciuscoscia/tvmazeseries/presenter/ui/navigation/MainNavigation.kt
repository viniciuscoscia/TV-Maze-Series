package com.viniciuscoscia.tvmazeseries.presenter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {

        }
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object TVShowDetailsScreen : Screen("tv_show_details_screen")
}