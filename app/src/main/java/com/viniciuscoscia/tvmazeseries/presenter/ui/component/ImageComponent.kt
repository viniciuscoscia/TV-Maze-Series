package com.viniciuscoscia.tvmazeseries.presenter.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.viniciuscoscia.tvmazeseries.R

@Composable
fun BigImage(
    imageUrl: String?,
    modifier: Modifier = Modifier.height(350.dp),
    contentScale: ContentScale = ContentScale.Fit
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentScale = contentScale,
        modifier = modifier,
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