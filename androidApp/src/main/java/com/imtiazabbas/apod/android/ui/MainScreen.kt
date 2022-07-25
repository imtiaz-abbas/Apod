package com.imtiazabbas.apod.android.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import comimtiazabbasapoddatadb.AstronomyPicture

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(navController: NavController, viewModel: APODViewModel) {
  LaunchedEffect(Unit, block = {
    viewModel.getAPOD()
  })

  LazyVerticalGrid(
    cells = GridCells.Fixed(2),
    content = {
      items(viewModel.picture) {
        ImageCard(picture = it, modifier = Modifier
          .clickable {
            navController.navigate(Screen.DetailScreen.withArgs(it.title, it.explanation))
          }
          .fillMaxWidth()
          .padding(16.dp))
      }
    }
  )
}


@Composable
fun ImageCard(picture: AstronomyPicture, modifier: Modifier) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(15.dp),
    elevation = 5.dp
  ) {
    Box(modifier = Modifier.height(200.dp)) {
      Image(painter = rememberImagePainter(picture.url), contentDescription = picture.title, contentScale = ContentScale.Crop)
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            brush = Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.Black
              )
            )
          )
      )
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(12.dp),
        contentAlignment = Alignment.BottomStart
      ) {
        Column {
          Text(text = picture.date.getReadableTime(), style = TextStyle(color = Color.White, fontSize = 14.sp))
          Text(text = picture.title, style = TextStyle(color = Color.White, fontSize = 12.sp))
        }
      }
    }
  }
}

@Preview
@Composable
fun ComposablePreview() {
  MainView(navController = rememberNavController(), APODViewModel())
}