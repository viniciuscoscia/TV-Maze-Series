@file:OptIn(ExperimentalMaterialApi::class)

package com.viniciuscoscia.tvmazeseries.presenter.ui.showdetails

import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.domain.model.EpisodeModel
import com.viniciuscoscia.tvmazeseries.domain.model.SeasonModel
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.navigation.Screen
import com.viniciuscoscia.tvmazeseries.presenter.ui.composables.*
import com.viniciuscoscia.tvmazeseries.presenter.ui.theme.Shapes
import com.viniciuscoscia.tvmazeseries.presenter.util.ObserveErrorState
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

    viewModel.ObserveErrorState()
    viewModel.fetchInfo(showId)
    ShowDetailsBody(viewModel, navController)
}

@Composable
private fun ShowDetailsBody(viewModel: TVShowDetailsViewModel, navController: NavController) {
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
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            if (episodeDetails.loading) {
                TVMazeShowLoading()
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
                            ShowSeasons(this, navController)
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
        ImageLoader(
            imageUrl = imageUrl,
            modifier = Modifier.height(350.dp)
        )

        ShowDetailsBox(tvShowModel)
    }
}

@Composable
fun ShowDetailsBox(tvShowModel: TVShowModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(Shapes.small)
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 8.dp),
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

        tvShowModel.genres?.run {
            ShowGenres(this)
        }
    }
}

@Composable
fun ShowGenres(genres: List<String>) {
    Row {
        TVMazeSimpleFieldText(
            stringResource(id = R.string.genres),
            fontWeight = FontWeight.ExtraBold,
            maxLines = 2
        )
        Spacer(modifier = Modifier.width(2.dp))
        genres.forEachIndexed { index, text ->
            TVMazeSimpleFieldText(
                text = if (index < genres.size - 1) {
                    "$text, "
                } else {
                    text
                },
                maxLines = 2
            )
        }
    }
}

@Composable
private fun ShowName(tvShowModel: TVShowModel) {
    TVMazeTitle(
        text = tvShowModel.name,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Summary(summary: String) {
    AndroidView(
        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
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
        TVMazeSimpleFieldText(text = "$fieldName:", fontWeight = FontWeight.ExtraBold, maxLines = 2)
        Spacer(modifier = Modifier.width(2.dp))
        TVMazeSimpleFieldText(
            text = fieldValue ?: stringResource(id = R.string.unknown),
            maxLines = 2
        )
    }
}

@Composable
fun ShowSeasons(seasons: List<SeasonModel>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TVMazeTitle(
            text = stringResource(id = R.string.seasons),
            textColor = Color.White,
        )

        seasons.forEach { season ->
            Spacer(modifier = Modifier.height(4.dp))
            ExpandableCard(
                title = "${stringResource(id = R.string.season)} ${season.seasonNumber} - ${
                    stringResource(
                        id = R.string.total_episodes
                    )
                } ${season.episodes.size}"
            ) {
                Episodes(season.episodes, navController)
            }
        }
    }
}

@Composable
fun Episodes(episodes: List<EpisodeModel>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shapes.small)
            .background(Color.White)
    ) {
        episodes.forEach {
            Episode(it, navController)
        }
    }
}

@Composable
fun Episode(episodeModel: EpisodeModel, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.TVShowEpisodeDetail.route + "/${episodeModel.id}")
            },
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            EpisodeImage(episodeModel)
            TVMazeSimpleFieldText(
                text = "${episodeModel.number} - ${episodeModel.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun EpisodeImage(episodeModel: EpisodeModel) {
    Row(modifier = Modifier.height(200.dp)) {
        ImageLoader(
            imageUrl = episodeModel.image,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}