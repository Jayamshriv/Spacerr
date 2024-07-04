package com.example.antrikshgyan.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppCustomBar(
    heading: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF1F1F1F),
    onBackClick : ()-> Unit,
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = Color.White
        ),
        title = {
            Text(
                text = heading,
                fontFamily = fonts,
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {

            IconButton(onClick = {
                onBackClick()
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(4.dp)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
    )


}