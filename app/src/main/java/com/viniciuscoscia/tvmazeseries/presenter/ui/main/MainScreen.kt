@file:OptIn(ExperimentalFoundationApi::class)

package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.navigation.Screen
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.*
import com.viniciuscoscia.tvmazeseries.presenter.util.ObserveErrorState
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = getViewModel()) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(id = R.string.tv_series))
                    SearchView(state = textState) { showName ->
                        if (showName.isBlank()) return@SearchView
                        Timber.d("Search for $showName")
                        navController.navigate(Screen.TVShowSearch.route + "/${showName}")
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 12.dp
        )
    }) {
        val showsPagingItems = viewModel.getTvShows().collectAsLazyPagingItems()
        viewModel.ObserveErrorState { showsPagingItems.refresh() }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            TVShowsGrid(navController = navController,
                tvShows = showsPagingItems,
                onPagingError = {
                    viewModel.onPagingError()
                }
            )
        }
    }
}

@Composable
private fun TVShowsGrid(
    navController: NavController,
    tvShows: LazyPagingItems<TVShowModel>,
    onPagingError: () -> Unit
) {
    val cellState by remember { mutableStateOf(CELL_COUNT) }

    LazyVerticalGrid(
        cells = GridCells.Fixed(cellState),
        content = {
            if (tvShows.loadState.refresh is LoadState.Error) {
                onPagingError()
                return@LazyVerticalGrid
            }
            items(tvShows.itemCount) { index ->
                val show = tvShows[index] ?: return@items
                TVShowCard(show = show, navController = navController)
            }
            renderLoading(tvShows.loadState)
        }
    )
}

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }
private fun LazyGridScope.renderLoading(loadState: CombinedLoadStates) {
    if (loadState.append is LoadState.Loading) {
        item(span = span) {
            LoadingColumn(modifier = Modifier.height(cardHeight))
        }
    } else if (loadState.refresh is LoadState.Loading) {
        item(span = span) {
            LoadingColumn(modifier = Modifier.fillParentMaxSize())
        }
    }
}