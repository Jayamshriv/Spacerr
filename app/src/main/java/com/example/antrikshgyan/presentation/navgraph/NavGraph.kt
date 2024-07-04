package com.example.antrikshgyan.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.presentation.apod.components.APODScreen
import com.example.antrikshgyan.presentation.apod.viewmodel.APODViewModel
import com.example.antrikshgyan.presentation.home_screen.components.HomeScreen

@Composable
fun NavGraph() {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.toString()){
        composable(route = Routes.APODScreen.toString()) {
            val apodModel = navController.previousBackStackEntry?.savedStateHandle?.get<APODModel>("apod")
            apodModel?.let { it1 ->
                APODScreen(
                    apodModel,
                    navController
                )
            }
        }
        composable(
            route = Routes.HomeScreen.toString()
        ){
            HomeScreen(navController = navController)
        }
    }
    
}