package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.ui.navigation.Screen
import org.koin.androidx.compose.inject

@Composable
fun MainScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        TVShowsList(navController)
    }
}

@Composable
private fun TVShowsList(navController: NavController) {
    val viewModel: MainViewModel by inject()
    val shows: LazyPagingItems<TVShowModel> = viewModel.getTvShows().collectAsLazyPagingItems()

    LazyColumn {
        items(shows) { show ->
            show?.run {
                TVShowCard(show, navController)
            }
        }
    }
}

@Composable
fun TVShowCard(show: TVShowModel, navController: NavController) {
    Row(
        Modifier
            .padding(2.dp)
            .background(Color.White)
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.TVShowDetailsScreen.route)
            }) {
        Image(
            painter = rememberAsyncImagePainter(show.imageSmallUrl),
            contentDescription = null,
            modifier = Modifier.size(width = 100.dp, height = 150.dp)
        )
        Column(Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = show.name,
                color = Color.Black,
            )
        }
    }
}
