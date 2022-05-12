package com.viniciuscoscia.tvmazeseries.presenter.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viniciuscoscia.tvmazeseries.R
import com.viniciuscoscia.tvmazeseries.presenter.util.ErrorState

@Composable
fun ErrorDialog(
    errorState: ErrorState,
    onDismiss: () -> Unit
) {
    if (errorState !is ErrorState.Error) return
    AlertDialog(
        title = {
            Text(
                text = errorState.title,
                fontSize = 18.sp
            )
        },
        text = {
            Text(errorState.message)
        },
        confirmButton = {
            Button(modifier = Modifier.padding(8.dp),
                onClick = {
                    onDismiss()
                }) {
                Text(stringResource(id = R.string.okay))
            }
        },
        onDismissRequest = {
            onDismiss()
        },
    )
}