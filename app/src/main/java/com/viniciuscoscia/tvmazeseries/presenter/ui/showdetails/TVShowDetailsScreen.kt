package com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TVShowDetailsScreen(
    navController: NavHostController,
    showId: Int,
    viewModel: TVShowDetailsViewModel = getViewModel()
) {
    if (showId == 0) {
        navController.popBackStack()
    }

    viewModel.fetchInfo(showId)

    ShowDetailsBody(viewModel)
}

@Composable
private fun ShowDetailsBody(viewModel: TVShowDetailsViewModel) {
    val episodeDetails = viewModel.episodeDetails

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = episodeDetails.showModel?.name ?: "Show Details")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 12.dp
            )
        },
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = episodeDetails.loading) {
                ShowLoading()
            }
            AnimatedVisibility(visible = episodeDetails.loading.not()) {
                episodeDetails.showModel?.run {
                    ShowDetails(this)
                }
                episodeDetails.seasons?.run {
                    ShowSeasons(this)
                }
            }
        }
    }
}

@Composable
fun ShowDetails(tvShowModel: TVShowModel) = with(tvShowModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ShowImage(tvShowModel.imageUrl)
    }
}

@Composable
private fun ShowImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .size(Size.ORIGINAL)
            .build()
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painter,
            contentDescription = ""
        )
    }
}

@Composable
fun ShowSeasons(seasons: List<SeasonModel>) = with(seasons) {
    Column(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
fun ShowLoading() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        CircularProgressIndicator()
    }
}