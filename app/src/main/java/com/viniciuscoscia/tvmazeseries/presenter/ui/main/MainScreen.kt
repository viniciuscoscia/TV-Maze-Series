package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = stringResource(id = R.string.tv_series_list))
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 12.dp
        )
    }) {
        Column(Modifier.fillMaxSize()) {
            TVShowsList(navController)
        }
    }
}

@Composable
private fun TVShowsList(
    navController: NavController,
    viewModel: MainViewModel = getViewModel()
) {
    val shows: LazyPagingItems<TVShowModel> = viewModel.getTvShows().collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState
    ) {
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
            .height(height = 200.dp)
            .clickable {
                navController.navigate(Screen.TVShowDetailsScreen.route + "/${show.id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = show.imageSmallUrl,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxHeight(),
            contentDescription = stringResource(R.string.poster_description)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator(modifier = Modifier.padding(50.dp))
            } else {
                SubcomposeAsyncImageContent()
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
