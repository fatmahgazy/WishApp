package org.codeforegypt.wishinglist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.codeforegypt.presentation.AddEditView
import org.codeforegypt.presentation.HomeScreen
import org.codeforegypt.presentation.WishViewModel

@Composable
fun Navigation(
    viewModel : WishViewModel = viewModel(),
    navController:NavHostController = rememberNavController(),
)
{

    NavHost(
        navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(viewModel,navController)
        }
        composable(Screen.AddScreen.route + "/{id}",
            arguments =
        listOf(navArgument("id"){
            type= NavType.LongType
            defaultValue = 0L
            nullable = false
        })
        ) { entry ->
            val id  = entry.arguments?.getLong("id") ?: 0L
            AddEditView(
                id = id,
                navController,
                viewModel
            )
        }

    }
}


