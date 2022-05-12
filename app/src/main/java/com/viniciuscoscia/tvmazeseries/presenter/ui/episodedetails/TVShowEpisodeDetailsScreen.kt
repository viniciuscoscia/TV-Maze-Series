package com.viniciuscoscia.tvmazeseries.presenter.ui.episodedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.presenter.ui.component.*
import com.viniciuscoscia.tvmazeseries.presenter.ui.theme.Shapes
import org.koin.androidx.compose.getViewModel

@Composable
fun TVShowEpisodeDetailsScreen(
    episodeId: Int,
    viewModel: TVShowEpisodeDetailsViewModel = getViewModel()
) {
    viewModel.fetchEpisodeInfo(episodeId)
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
                            text = stringResource(id = R.string.episode_details)
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
            val uiModel = viewModel.episodeDetails.value
            if (uiModel.loading) {
                TVMazeShowLoading()
            } else {
                AnimatedVisibility(visible = uiModel.loading.not()) {
                    if (uiModel.episodeDetails != null) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            val details = uiModel.episodeDetails
                            details.image?.run {
                                ImageLoader(
                                    imageUrl = details.image,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(all = 16.dp)
                                    .clip(Shapes.small)
                                    .background(Color.White)
                                    .padding(all = 8.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(8.dp))
                                TVMazeTitle(text = "${details.number} - ${details.name}")

                                TVMazeSimpleFieldText(
                                    text = "${stringResource(id = R.string.season)} ${details.season}",
                                    maxLines = 2
                                )

                                details.summary?.run {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    TVMazeHtmlView(htmlCode = this)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}