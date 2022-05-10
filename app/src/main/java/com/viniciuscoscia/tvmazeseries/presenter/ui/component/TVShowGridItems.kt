@file:OptIn(ExperimentalFoundationApi::class)

package com.viniciuscoscia.tvmazeseries.presenter.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.navigation.Screen

const val CELL_COUNT = 2
val cardHeight = 300.dp

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

@Composable
fun LoadingColumn(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}