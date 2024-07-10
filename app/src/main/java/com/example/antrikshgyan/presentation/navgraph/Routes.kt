package com.example.antrikshgyan.presentation.navgraph

sealed class Routes(
    val routes: String
) {
    data object HomeScreen : Routes("HomeScreen")
    data object APODScreen : Routes("APODScreen")
    data object ISROScreen : Routes("ISROScreen")
    data object MarsRoverScreen : Routes("MarsRoverScreen")
}