package com.example.antrikshgyan.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun APODScreen(navController: NavController) {
    Column {
        Text(text = "APO", fontSize = 68.sp)
    }
}