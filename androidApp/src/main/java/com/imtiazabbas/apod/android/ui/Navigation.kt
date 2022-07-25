package com.imtiazabbas.apod.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


sealed class Screen(val route: String) {
  object  MainScreen: Screen("main_screen")
  object  DetailScreen: Screen("detail_screen")

  fun withArgs(vararg args: String): String {
    return buildString {
      append(route)
      args.forEach {
        append("/${it}")
      }
    }
  }
}

@Composable
fun Navigation() {
  val navController = rememberNavController()
  val viewModel = APODViewModel()
  NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
    composable(route = Screen.MainScreen.route) {
      MainView(navController = navController, viewModel = viewModel)
    }
    composable(
      route = Screen.DetailScreen.route + "/{title}/{explanation}",
      arguments = listOf(
        navArgument("title") {
          type = NavType.StringType
          nullable = false
        },
        navArgument("explanation") {
          type = NavType.StringType
          nullable = false
        }
      )
    ) { entry ->
      DetailScreen(
        title = entry.arguments?.getString("title") ?: "",
        explanation = entry.arguments?.getString("explanation") ?: ""
      )
    }
  }
}


@Composable
fun DetailScreen(title: String, explanation: String) {
  Box(modifier = Modifier.fillMaxSize()) {
    Column {
      Text(text = title)
      Text(text = explanation)
    }
  }
}