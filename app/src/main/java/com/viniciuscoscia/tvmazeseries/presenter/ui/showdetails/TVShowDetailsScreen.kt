package com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun TVShowDetailsScreen(navController: NavHostController, showId: String) {
    if (showId.isBlank()) {
        navController.popBackStack()
    }
    Text(showId)
}