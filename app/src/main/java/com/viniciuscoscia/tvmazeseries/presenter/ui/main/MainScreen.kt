package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
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
                Text(this.name)
            }
        }
    }
}