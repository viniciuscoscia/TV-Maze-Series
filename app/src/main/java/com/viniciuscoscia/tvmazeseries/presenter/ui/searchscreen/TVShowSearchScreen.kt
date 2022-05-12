@file:OptIn(ExperimentalFoundationApi::class)

package com.viniciuscoscia.tvmazeseries.presenter.ui.searchscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.CELL_COUNT
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.TVMazeShowLoading
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.TVMazeSimpleFieldText
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.TVShowCard
import com.viniciuscoscia.tvmazeseries.presenter.util.ObserveErrorState
import org.koin.androidx.compose.getViewModel

@Composable
fun TVShowSearchScreen(
    navController: NavHostController,
    showName: String,
    viewModel: TVShowSearchViewModel = getViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(id = R.string.search_for, showName))
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
        )
    }) {
        viewModel.searchForShowByName(showName)
        viewModel.ObserveErrorState()
        Column(Modifier.fillMaxSize()) {
            val uiModel = viewModel.shows.value

            when {
                uiModel.loading -> {
                    TVMazeShowLoading()
                }
                uiModel.shows.isNullOrEmpty() -> {
                    ShowNoResultsFound(showName)
                }
                else -> {
                    val cellState by remember { mutableStateOf(CELL_COUNT) }
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(cellState)
                    ) {
                        items(uiModel.shows.size) { index ->
                            val show = uiModel.shows[index]
                            TVShowCard(show = show, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowNoResultsFound(showName: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TVMazeSimpleFieldText(
            text = stringResource(
                id = R.string.no_results_found_for, showName
            )
        )
    }
}