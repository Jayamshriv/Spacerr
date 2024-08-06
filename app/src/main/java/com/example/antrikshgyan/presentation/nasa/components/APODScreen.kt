package com.example.antrikshgyan.presentation.nasa.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.R
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.common.FullScreenImage
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.nasa.viewmodel.APODViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APODScreen(
    heading: String = "Astronomy POD",
    navController: NavController
) {
    val apodViewModel: APODViewModel = hiltViewModel()
    val TAG = "APODScreen"
    val apodState = apodViewModel.state
    var heightOfCard by remember {
        mutableStateOf(300.dp)
    }
    var showImageDialog by remember {
        mutableStateOf(false)
    }
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.astars_bg),
            contentDescription = "bg",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.43f),
            contentScale = ContentScale.FillBounds
        )

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF1F1F1F),
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar =
            {
                TopAppCustomBar(
                    heading = heading,
                    scrollBehavior = scrollBehavior
                ) {
                    navController.navigate(Routes.HomeScreen.routes)
                }
            }
        ) { innerPadding ->
            Log.e("padding", innerPadding.toString())

            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Log.e(TAG, apodState.toString())

                if (!apodState.isLoading and apodState.error.isEmpty()) {
                    val apod = apodState.apod
                    Text(
                        text = apod.title!!,
                        fontFamily = fonts,
                        color = Color.White,
                        fontSize = 18.sp,
                        lineHeight = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    val youtubeUrl = apod.url
                    //            https://www.youtube.com/embed/1R5QqhPq1Ik?rel=0
                    val videoId = youtubeUrl?.substringAfter("embed/")?.substringBefore("?rel")
                    var url = ""

                    if (apod.media_type == "video") {
                        heightOfCard = 200.dp
                        url = "https://img.youtube.com/vi/$videoId/mqdefault.jpg"

                        YoutubePlayer(
                            modifier = Modifier
                                .fillMaxSize(),
                            youtubeVideoId = videoId.toString(),
                            lifecycleOwner = LocalLifecycleOwner.current,
                        )
                    } else {
                        if (apod.hdurl != null) {
                            url = apod.hdurl!!
                        } else {
                            url = apod.url.toString()
                        }
                        if (showImageDialog) {
                            FullScreenImage(url = apodState.apod.hdurl ?: apodState.apod.url) {
                                showImageDialog = false
                            }
                        }
                        SubcomposeAsyncImage(
                            model = url,
                            loading = {
                                CenteredCircularProgress()
                            },
                            contentDescription = "image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(heightOfCard)
                                .padding(vertical = 8.dp)
                                .background(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color.Transparent
                                )
                                .border(
                                    BorderStroke(
                                        0.4.dp, color = Color.White
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .shadow(
                                    elevation = 16.dp,
                                    ambientColor = Purple,
                                    spotColor = Purple,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    showImageDialog = true
                                }
                        )


                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Behind The Picture :",
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontFamily = fonts,
                            color = Color.White,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Justify,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        ExtendedFloatingActionButton(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            elevation = FloatingActionButtonDefaults.elevation(0.dp,0.dp,0.dp,0.dp),
                            onClick = {
                                val data = hashMapOf(
                                    "title" to apod.title,
                                    "body" to apod.explanation,
                                    "imageUrl" to apod.url
                                )
                                Firebase.firestore.collection("notifications")
                                    .document()
                                    .set(data)
                                    .addOnCompleteListener {task->
                                        if(!task.isSuccessful){
                                            Log.e(TAG, "error : ${task.exception}")
                                        }else{
                                            Log.w(TAG,"data added : $data")
                                        }
                                    }
                            },
                            modifier = Modifier.background(color = Color.Transparent).size(12.dp)
                        ) {

                        }
                    }

                    Text(
                        text = apod.explanation!!,
                        fontFamily = fonts,
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.bodySmall
                    )


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
}

//}