package com.example.antrikshgyan.presentation.home_screen.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun SpaceXHomeScreen(
    navController: NavController
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            text = "ISRO Zone",
            fontFamily = fonts,
            color = Color.White,
            fontSize = 18.sp,
            lineHeight = 25.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
//            CardHomeScreen(heading = "Spacecraft", image = R.drawable.spacecraft_){
//
//            }
//            CardHomeScreen(heading = "Launches", image = R.drawable.launchpad){}
//            CardHomeScreen(heading = "Satellites", image = R.drawable.spacecraft_){}
//            CardHomeScreen(heading = "Centres", image = R.drawable.spacecraft_){}
        }

    }

}