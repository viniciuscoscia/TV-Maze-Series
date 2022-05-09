package com.viniciuscoscia.tvmazeseries.presenter.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import com.viniciuscoscia.tvmazeseries.presenter.navigation.NavigationComponent
import com.viniciuscoscia.tvmazeseries.presenter.theme.TVMazeSeriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVMazeSeriesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavigationComponent()
                }
            }
        }
    }
}