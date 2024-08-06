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
fun NASAZoneHomeScreen(
    navController: NavController
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 32.dp)
    ) {

        Text(
            text = "NASA Zone",
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
//            CardHomeScreen(heading = "IVL", image = R.drawable.spacecraft_){
//
//            }
//            CardHomeScreen(heading = "Mars Lust", image = R.drawable.launchpad){
//
//            }
//            CardHomeScreen(heading = "EPIC", image = R.drawable.spacecraft_){
//
//            }
        }

    }

}