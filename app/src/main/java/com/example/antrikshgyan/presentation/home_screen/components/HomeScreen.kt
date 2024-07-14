package com.example.antrikshgyan.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.antrikshgyan.R

@Composable
fun HomeScreen(
    navController: NavController
) {

    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.astars_bg),
            contentDescription = "bg",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.425f)
            ,
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            modifier = Modifier.padding(8.dp).padding(bottom = 48.dp)
        ) {
            item { HeadingHomeScreen() }
            item { APODHomeScreen(navController = navController) }
            item { ExploreHomeScreen(navController = navController) }
            item { ISROZoneHomeScreen(navController = navController) }
//        item { NASAZoneHomeScreen(navController = navController) }
        }
    }
}
