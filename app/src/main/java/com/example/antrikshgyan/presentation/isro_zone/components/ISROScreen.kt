package com.example.antrikshgyan.presentation.isro_zone.components

import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.antrikshgyan.R
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.isro_zone.viewmodel.ISROServiceViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ISROScreen(
    heading : String,
    index : Int,
    navController: NavController,

) {
    val isroServiceViewModel : ISROServiceViewModel = hiltViewModel()
    val responseList = isroServiceViewModel.spacecraftResponse.value.spacecraft
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
                TopAppCustomBar(heading = heading, scrollBehavior = scrollBehaviour) {
                    navController.navigate(Routes.HomeScreen.toString())
                }
            }
        ) { innerPadding->
            Log.e("innerPadding", innerPadding.toString())

            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                items(responseList){item->
                    when(index){
                        1 -> {
                            ISROSpacecraft(spacecraft = item)
                        }
                        2 -> {
                            ISROSpacecraft(spacecraft = item)
                        }
                        3 -> {
                            ISROSpacecraft(spacecraft = item)
                        }
                        4 -> {
                            ISROSpacecraft(spacecraft = item)
                        }

                    }
                    Log.e("SPACE Craft", item.toString())
                }
            }

        }
    }
}