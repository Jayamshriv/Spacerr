package com.example.antrikshgyan.presentation.mars.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.antrikshgyan.presentation.mars.state.MarsRoverImageDataState
import com.example.antrikshgyan.presentation.mars.viewmodel.MarsRoverImageViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Composable
fun MarsRoverScreen() {

    val viewModel: MarsRoverImageViewModel = hiltViewModel()

    viewModel.getMarRoverImage(23, 1)

    val list = viewModel.marsRoverImageViewModel.value.marsRoverImage.photos

    LazyColumn {
        items(list) { item ->
            Text(
                text = item.toString(),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

}