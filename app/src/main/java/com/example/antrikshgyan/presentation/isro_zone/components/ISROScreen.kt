package com.example.antrikshgyan.presentation.isro_zone.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.ISROSpaceCraftModel
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.isro_zone.viewmodel.ISROServiceViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ISROScreen(
    heading: String,
    index: Int,
    navController: NavController,

    ) {
    val isroServiceViewModel: ISROServiceViewModel = hiltViewModel()
    val spacecraftState = isroServiceViewModel.spacecraftResponse.value.spacecraft
    val launchState = isroServiceViewModel.launchState.value.launch
    var revealContent by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(id = R.drawable.astars),
            contentDescription = "astars",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.43f),
            contentScale = ContentScale.FillBounds
        )

        val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF1F1F1F),
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                TopAppCustomBar(
                    heading = heading,
                    scrollBehavior = scrollBehaviour
                ) {
//                    navController.popBackStack(Routes.HomeScreen.routes, false)
//                    navController.navigate(Routes.HomeScreen.routes)
                    navController.navigate(Routes.HomeScreen.routes) { popUpTo(Routes.HomeScreen.routes) }
                }
            }
        ) { innerPadding ->
            Log.e("innerPadding", innerPadding.toString())

            LaunchedEffect(key1 = true) {
                delay(1000)
                revealContent = true
            }
            if (revealContent) {
                when (index) {
                    1 -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(color = Color.Transparent)
                        ) {
                            items(items = spacecraftState) { item ->
                                ISROSpacecraft(spacecraft = item)
                            }
                        }

                    }

                    2 -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(color = Color.Transparent)
                        ) {
                            items(items = launchState) { item ->
                                ISROLaunches(launch = item)
                            }
                        }

                    }

                    3 -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(color = Color.Transparent)
                        ) {
                            items(items = spacecraftState) { item ->
                                ISROSpacecraft(spacecraft = item)
                            }
                        }

                    }

                    4 -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(color = Color.Transparent)
                        ) {
                            items(items = spacecraftState) { item ->
                                ISROSpacecraft(spacecraft = item)
                            }
                        }

                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = Blue,
                        trackColor = Color(0xFF4D4949)
                    )
                }
            }
        }
    }
}
//}