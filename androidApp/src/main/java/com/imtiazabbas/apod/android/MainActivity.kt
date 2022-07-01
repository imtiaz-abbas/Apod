package com.imtiazabbas.apod.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val apodViewModel = APODViewModel()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainView(apodViewModel)
            }
        }
    }
}

@Composable
fun MainView(viewModel: APODViewModel) {
    LaunchedEffect(Unit, block = {
        viewModel.getAPOD()
    })
    Column(
        Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = viewModel.picture.explanation)
    }
}

@Preview
@Composable
fun ComposablePreview() {
    MainView(APODViewModel())
}