package com.example.antrikshgyan.presentation.navgraph

sealed class Routes(
    val routes: String
) {
    object HomeScreen : Routes("HomeScreen")
    object APODScreen : Routes("APODScreen")
}