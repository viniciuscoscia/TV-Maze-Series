package com.viniciuscoscia.tvmazeseries.presenter.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TVMazeSimpleFieldText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Medium
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        textAlign = textAlign,
        fontWeight = fontWeight,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun TVMazeTitle(
    text: String,
    textColor: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        textAlign = textAlign,
        color = textColor,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        style = MaterialTheme.typography.h5
    )
}