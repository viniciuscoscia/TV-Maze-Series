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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.presenter.ui.component.CELL_COUNT
import com.viniciuscoscia.tvmazeseries.presenter.ui.component.TVMazeShowLoading
import com.viniciuscoscia.tvmazeseries.presenter.ui.component.TVShowCard
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
            elevation = 12.dp
        )
    }) {
        viewModel.searchForShowByName(showName)
        viewModel.ObserveErrorState()
        Column(Modifier.fillMaxSize()) {
            val shows = viewModel.shows
            if (shows.value.isEmpty()) {
                TVMazeShowLoading()
                return@Column
            }

            val cellState by remember { mutableStateOf(CELL_COUNT) }
            LazyVerticalGrid(
                cells = GridCells.Fixed(cellState)
            ) {
                items(shows.value.size) { index ->
                    val show = shows.value[index]
                    TVShowCard(show = show, navController = navController)
                }
            }
        }
    }
}