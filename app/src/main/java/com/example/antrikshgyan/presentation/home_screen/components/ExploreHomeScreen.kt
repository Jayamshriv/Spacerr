package com.example.antrikshgyan.presentation.home_screen.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antrikshgyan.R
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun ExploreHomeScreen(
    navController: NavController
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            text = "Explore Ship",
            fontFamily = fonts,
            color = Color.White,
            fontSize = 18.sp,
            lineHeight = 25.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CardHomeScreen(heading = "Daily Shit", image = R.drawable.spacecraft_, modifier = Modifier.size(140.dp), cornerSize = 20.dp){
                navController.navigate(Routes.DailyFactsScreen.routes)
            }
            CardHomeScreen(heading = "Locate ISS", image = R.drawable.spacecraft_, modifier = Modifier.size(140.dp), cornerSize = 20.dp){
                navController.navigate(Routes.ISSLocationScreen.routes)
            }
            CardHomeScreen(heading = "MARS Rover Images", image = R.drawable.spacecraft_, modifier = Modifier.size(140.dp), cornerSize = 20.dp){
                navController.navigate(Routes.MarsRoverScreen.routes)
            }
            CardHomeScreen(heading = "Insight 3D", image = R.drawable.spacecraft_, modifier = Modifier.size(140.dp), cornerSize = 20.dp){
                navController.navigate(Routes.Insight3DScreen.routes)
            }
            CardHomeScreen(heading = "Sonification", image = R.drawable.spacecraft_, modifier = Modifier.size(140.dp), cornerSize = 20.dp){

            }

        }

    }

}