package com.viniciuscoscia.tvmazeseries.presenter.ui.composables

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.viniciuscoscia.tvmazeseries.R

@Composable
fun TVMazeHtmlView(htmlCode: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).also {
                it.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black
                    )
                )
            }
        },
        update = {
            it.text = HtmlCompat.fromHtml(
                htmlCode,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }
    )
}