@file:OptIn(ExperimentalFoundationApi::class)

package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.navigation.Screen
import com.viniciuscoscia.tvmazeseries.presenter.ui.component.SearchView
import kotlinx.coroutines.flow.Flow
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
                    SearchView(state = textState) {
                        Timber.d("Search for $it")
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 12.dp
        )
    }) {
        Column(Modifier.fillMaxSize()) {
            TVShowsList(navController, viewModel.getTvShows())
        }
    }
}

@Composable
private fun TVShowsList(
    navController: NavController,
    tvShows: Flow<PagingData<TVShowModel>>
) {
    val cellState by remember { mutableStateOf(2) }
    val shows: LazyPagingItems<TVShowModel> = tvShows.collectAsLazyPagingItems()

    LazyVerticalGrid(
        cells = GridCells.Fixed(cellState),
        content = {
            items(shows.itemCount) { index ->
                val show = shows[index] ?: return@items
                TVShowCard(show = show, navController = navController)
            }
            renderLoading(shows.loadState)
        }
    )
}

@Composable
fun TVShowCard(show: TVShowModel, navController: NavController) {
    Column(
        Modifier
            .padding(2.dp)
            .background(Color.White)
            .height(cardHeight)
            .clickable {
                navController.navigate(Screen.TVShowDetailsScreen.route + "/${show.id}")
            },
    ) {
        SubcomposeAsyncImage(
            model = show.imageSmallUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .height(height = 250.dp),
            contentDescription = stringResource(R.string.poster_description)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator(modifier = Modifier.padding(50.dp))
            } else {
                SubcomposeAsyncImageContent()
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = show.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private const val CELL_COUNT = 2
private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }
private val cardHeight = 300.dp

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

@Composable
private fun LoadingColumn(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}