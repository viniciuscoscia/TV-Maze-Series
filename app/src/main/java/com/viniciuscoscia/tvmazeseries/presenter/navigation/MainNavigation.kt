package com.viniciuscoscia.tvmazeseries.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.viniciuscoscia.tvmazeseries.presenter.ui.main.MainScreen
import com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails.TVShowDetailsScreen

private const val tvShowId = "tv_show_id"

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.TVShowDetailsScreen.route + "/{$tvShowId}",
            arguments = listOf(
                navArgument(tvShowId) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            TVShowDetailsScreen(navController, entry.arguments!!.getInt(tvShowId, 0))
        }
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object TVShowDetailsScreen : Screen("tv_show_details_screen")
}