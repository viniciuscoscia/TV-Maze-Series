@file:OptIn(ExperimentalMaterialApi::class)

package com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails

import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.domain.model.EpisodeModel
import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.ui.component.ExpandableCard
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
                        Text(
                            text = stringResource(id = R.string.show_details)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 12.dp
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            if (episodeDetails.loading) {
                ShowLoading()
            } else {
                AnimatedVisibility(visible = episodeDetails.loading.not()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
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
    }
}

@Composable
fun ShowDetails(tvShowModel: TVShowModel) = with(tvShowModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowImage(tvShowModel.imageUrl)

        ShowDetailsBox(tvShowModel)
    }
}

@Composable
fun ShowDetailsBox(tvShowModel: TVShowModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowName(tvShowModel)

        tvShowModel.summary?.run {
            Summary(this)
        }

        ShowField(
            fieldName = stringResource(id = R.string.premiered),
            fieldValue = tvShowModel.premiered
        )
        ShowField(
            fieldName = stringResource(id = R.string.ended),
            fieldValue = tvShowModel.ended
        )
        ShowGenres(tvShowModel.genres)
    }
}

@Composable
fun ShowGenres(genres: List<String>) {
    Row {
        Text(
            stringResource(id = R.string.genres),
            color = Color.Black,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(2.dp))
        genres.forEachIndexed { index, text ->
            Text(
                text = if (index < genres.size - 1) {
                    "$text, "
                } else {
                    text
                },
                color = Color.Black,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
private fun ShowImage(imageUrl: String) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Fit,
        modifier = Modifier.width(250.dp),
        contentDescription = stringResource(R.string.poster_description)
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator(modifier = Modifier.padding(100.dp))
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}

@Composable
private fun ShowName(tvShowModel: TVShowModel) {
    Text(
        text = tvShowModel.name,
        textAlign = TextAlign.Center,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun Summary(summary: String) {
    AndroidView(
        modifier = Modifier.padding(horizontal = 8.dp),
        factory = { context ->
            TextView(context).also {
                it.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        },
        update = { it.text = HtmlCompat.fromHtml(summary, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Composable
fun ShowField(fieldName: String, fieldValue: String?) {
    Row(horizontalArrangement = Arrangement.Center) {
        Text(
            text = "$fieldName:",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = fieldValue ?: stringResource(id = R.string.unknown),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun ShowSeasons(seasons: List<SeasonModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.seasons),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.h5
        )

        seasons.forEach { season ->
            ExpandableCard(
                title = "${stringResource(id = R.string.season)} ${season.seasonNumber} - ${
                    stringResource(
                        id = R.string.total_episodes
                    )
                } ${season.episodes.size}",

                ) {
//                Text(
//                    text = "${stringResource(id = R.string.season)} ${season.seasonNumber}",
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                    style = MaterialTheme.typography.h5
//                )
//                Text(
//                    text = "${stringResource(id = R.string.total_episodes)} ${season.episodes.size}",
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                    style = MaterialTheme.typography.h6
//                )
                Episodes(season.episodes)
            }
        }
    }
}

@Composable
fun Episodes(episodes: List<EpisodeModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        episodes.forEach {

        }
    }
}

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}