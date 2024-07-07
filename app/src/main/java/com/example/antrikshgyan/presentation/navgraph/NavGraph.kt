package com.example.antrikshgyan.presentation.navgraph

import android.os.Bundle
import android.util.Log
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
import com.example.antrikshgyan.presentation.isro_zone.components.ISROScreen
import com.example.antrikshgyan.presentation.isro_zone.viewmodel.ISROServiceViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes){
        composable(route = Routes.APODScreen.routes) {
            val apodModel = navController.previousBackStackEntry?.savedStateHandle?.get<APODModel>("apod")
            apodModel?.let { it1 ->
                APODScreen(
                    "Astronomy POD",
                    apodModel,
                    navController
                )
            }
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
    }
}