package com.viniciuscoscia.tvmazeseries.presenter.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.viniciuscoscia.tvmazeseries.domain.model.TVShowModel
import com.viniciuscoscia.tvmazeseries.presenter.navigation.Screen

const val CELL_COUNT = 2
val cardHeight = 325.dp

@Composable
fun TVShowCard(show: TVShowModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(2.dp),
        elevation = 12.dp
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .background(Color.White)
                .clickable {
                    navController.navigate(Screen.TVShowDetailsScreen.route + "/${show.id}")
                },
        ) {
            ImageLoader(
                imageUrl = show.imageSmallUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 260.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TVMazeSimpleFieldText(
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center,
                    text = show.name,
                    textColor = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
            }
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