package com.example.antrikshgyan.presentation.nasa.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.antrikshgyan.R
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.nasa.viewmodel.APODByCountViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyFactsScreen(
    navController: NavController
) {
    val TAG = "DailyFactsScreen"
    val apodViewModel: APODByCountViewModel = hiltViewModel()
    LaunchedEffect(Unit){
        apodViewModel.getAPODByCount(20)
    }
    val dailyFactState by apodViewModel.apodList.collectAsState()

    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.daily_fact_bg),
            contentDescription = "bg",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.15f),
            contentScale = ContentScale.FillBounds
        )

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar =
            {
                TopAppCustomBar(
                    heading = "Check Karo",
                    scrollBehavior = scrollBehavior
                ) {
                    navController.popBackStack()
                    navController.navigate(Routes.HomeScreen.routes)
                }
            }
        ) { innerPadding ->
            Log.e("padding", innerPadding.toString())

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(color = Color.Transparent)
            ) {
                when {
                    dailyFactState.isLoading -> {
                        Log.e(TAG, "Loading")
                        CenteredCircularProgress()
                    }

                    dailyFactState.error.isNullOrBlank().not() -> {
                        Log.e(TAG, "Error: ${dailyFactState.error}")
                        Text(text = "Error: ${dailyFactState.error}")
                    }

                    else -> {
                        Log.e(TAG, "Response Successful $dailyFactState")
                        LazyColumn {
                            items(dailyFactState.apodList!!) { item ->
                                DailyFactItemCard(item)
                            }
                        }
                    }
                }
            }
        }
    }
}
