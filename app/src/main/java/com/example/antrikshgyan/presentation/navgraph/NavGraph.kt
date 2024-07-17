package com.example.antrikshgyan.presentation.navgraph

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.antrikshgyan.domain.model.nasa.mars.PhotoModel
import com.example.antrikshgyan.presentation.nasa.components.APODScreen
import com.example.antrikshgyan.presentation.home_screen.components.HomeScreen
import com.example.antrikshgyan.presentation.isro_zone.components.ISROScreen
import com.example.antrikshgyan.presentation.mars.components.MarsRoverDetailsScreen
import com.example.antrikshgyan.presentation.mars.components.MarsRoverScreen
import com.example.antrikshgyan.presentation.nasa.components.DailyFactsScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes){
        composable(route = Routes.APODScreen.routes) {
                APODScreen(
                    "Astronomy POD",
                    navController
                )
        }
        composable(
            route = Routes.HomeScreen.routes
        ){
            HomeScreen(navController = navController)
        }

        composable(
            route = Routes.ISROScreen.routes
        ) {
            val argument = navController.previousBackStackEntry?.savedStateHandle?.get<Bundle>("argument")!!
            val index =  argument.getInt("index")
            val heading =  argument.getString("heading")!!
            Log.e("ARGUMENT","$index, $heading")
            ISROScreen(
                heading = heading,
                index = index,
                navController = navController)
        }
        composable(
            route = Routes.MarsRoverScreen.routes
        ) {
            MarsRoverScreen(
                navController
            )
        }
        composable(
            route = Routes.MarsRoverDetailsScreen.routes
        ) {
            val photoModel= navController.previousBackStackEntry?.savedStateHandle?.get<PhotoModel>("roverImage")
            Log.e("roverImage", photoModel.toString())
            MarsRoverDetailsScreen(navController = navController, photoModel =photoModel )
        }
        composable(
            route = Routes.DailyFactsScreen.routes
        ){
            DailyFactsScreen(
                navController = navController
            )
        }
    }
}