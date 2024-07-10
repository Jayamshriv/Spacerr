package com.example.antrikshgyan.presentation.home_screen.components

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antrikshgyan.R
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun ISROZoneHomeScreen(
    navController: NavController
) {
    val bundle = Bundle()
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
//        HorizontalDivider(
//            color = Color(0x801D1D1D),
//            thickness = 12.dp,
//            modifier = Modifier.width(12.dp)
//        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            modifier = Modifier.heightIn(max = 500.dp)
//                .horizontalScroll(rememberScrollState())
//                .fillMaxWidth()
        ) {
            itemsIndexed(listOf(1, 2, 3, 4)) { index, item ->
                when (item) {
                    1-> {
                        CardHomeScreen(heading = "Spacecraft", image = R.drawable.spacecraft_) {
                            Log.e("ARGUMENT", "Spacecraft")
                            bundle.putInt("index", 1)
                            bundle.putString("heading", "Spacecraft")
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "argument",
                                bundle
                            )
                            navController.navigate(Routes.ISROScreen.routes)
                        }
                    }

                    2 -> {
                        CardHomeScreen(heading = "Launches", image = R.drawable.launchpad) {
                            bundle.putInt("index", 2)
                            bundle.putString("heading", "Launches")
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "argument",
                                bundle
                            )
                            navController.navigate(Routes.ISROScreen.routes)
                        }
                    }

                    3 -> {
                        CardHomeScreen(heading = "Satellites", image = R.drawable.spacecraft_) {
                            bundle.putInt("index", 3)
                            bundle.putString("heading", "Satellites")
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "argument",
                                bundle
                            )
                            navController.navigate(Routes.ISROScreen.routes)
                        }
                    }

                    4 -> {
                        CardHomeScreen(heading = "Centres", image = R.drawable.spacecraft_) {
                            bundle.putInt("index", 4)
                            bundle.putString("heading", "Centres")
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "argument",
                                bundle
                            )
                            navController.navigate(Routes.ISROScreen.routes)
                        }
                    }
                }
            }
        }
    }
}







