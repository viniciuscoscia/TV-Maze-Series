package com.viniciuscoscia.tvmazeseries.presenter.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TVMazeSimpleFieldText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Medium,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        textAlign = textAlign,
        fontWeight = fontWeight,
        maxLines = maxLines,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun TVMazeTitle(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        textAlign = textAlign,
        color = textColor,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.h5
    )
}