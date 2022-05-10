package com.viniciuscoscia.tvmazeseries.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.viniciuscoscia.tvmazeseries.presenter.ui.episodedetails.TVShowEpisodeDetailsScreen
import com.viniciuscoscia.tvmazeseries.presenter.ui.main.MainScreen
import com.viniciuscoscia.tvmazeseries.presenter.ui.searchscreen.TVShowSearchScreen
import com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails.TVShowDetailsScreen

private const val showIdArg = "showIdRoute"
private const val episodeIdArg = "episodeIdArg"
private const val showNameArg = "showNameArg"

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
            route = Screen.TVShowDetailsScreen.route + "/{$showIdArg}",
            arguments = listOf(
                navArgument(showIdArg) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            TVShowDetailsScreen(navController, entry.arguments!!.getInt(showIdArg, 0))
        }

        composable(
            route = Screen.TVShowEpisodeDetail.route + "/{$episodeIdArg}",
            arguments = listOf(
                navArgument(episodeIdArg) {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            TVShowEpisodeDetailsScreen(entry.arguments!!.getInt(episodeIdArg, 0))
        }

        composable(
            route = Screen.TVShowSearch.route + "/{$showNameArg}",
            arguments = listOf(
                navArgument(showNameArg) {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            TVShowSearchScreen(navController, entry.arguments!!.getString(showNameArg, ""))
        }
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object TVShowDetailsScreen : Screen("tv_show_details_screen")
    object TVShowEpisodeDetail : Screen("tv_show_episode_detail")
    object TVShowSearch : Screen("tv_show_search")
}