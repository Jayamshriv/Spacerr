package com.example.antrikshgyan.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        item { HeadingHomeScreen() }
        item { APODHomeScreen(navController = navController) }
        item { ExploreHomeScreen(navController = navController) }
        item { ISROZoneHomeScreen(navController = navController) }
//        item { NASAZoneHomeScreen(navController = navController) }
    }

}
