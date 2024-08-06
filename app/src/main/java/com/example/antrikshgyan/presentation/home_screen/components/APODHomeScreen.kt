package com.example.antrikshgyan.presentation.home_screen.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.nasa.components.YoutubePlayer
import com.example.antrikshgyan.presentation.nasa.viewmodel.APODViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun APODHomeScreen(
    navController: NavController
) {
    val TAG = "APODHomeScreen"
    val apodViewModel: APODViewModel = hiltViewModel()
    val response = apodViewModel.state
    var heightOfCard by remember {
        mutableStateOf(275.dp)
    }

    val localDensity = LocalDensity.current
    Log.e("APODHomeScreen1", heightOfCard.toString())
    Column (
        modifier = Modifier.padding(horizontal = 8.dp)
    ){
        Card(
            modifier = Modifier
                .padding(top = 14.dp, bottom = 8.dp)
                .fillMaxWidth()
                .height(heightOfCard)
                .border(
                    BorderStroke(
                        0.4.dp, brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White,
                                Purple
                            )
                        )
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(12.dp))
                )
                .shadow(
                    elevation = 6.dp,
                    ambientColor = Purple,
                    spotColor = Purple,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(Color.Transparent),
        ) {
            Log.e("APODHomeScreen2", heightOfCard.toString())

            if (response.isLoading) {
                Log.e(TAG, "Loading")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
//                    Image(painter = painterResource(id = R.drawable.), contentDescription = )
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = Blue,
                        trackColor = Color(0xFF4D4949)
                    )
                }

            } else {
                Log.e(TAG, "Response Successful")
                val state = response.apod
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "apod",
                                state
                            )
                            navController.navigate(Routes.APODScreen.routes)
                        }
                )
                {
                    val youtubeUrl = state.url
                    //            https://www.youtube.com/embed/1R5QqhPq1Ik?rel=0
                    val videoId = youtubeUrl?.substringAfter("embed/")?.substringBefore("?rel")
                    var url = ""

                    if (state.media_type == "video") {
                        heightOfCard = 200.dp
                        url = "https://img.youtube.com/vi/$videoId/mqdefault.jpg"

                        YoutubePlayer(
                            modifier = Modifier
                                .fillMaxSize(),
                            //                        .onSizeChanged {
                            //                            heightOfCard = with(localDensity){
                            //                                it.height.toDp()
                            //                            }
                            //                            Log.e("APODHomeScreen3", heightOfCard.toString())
                            //                            }
                            //                        .onGloballyPositioned { coordinates ->
                            //                            heightOfCard = with(localDensity) { coordinates.size.height.toDp() }
                            //                        }
                            youtubeVideoId = videoId.toString(),
                            lifecycleOwner = LocalLifecycleOwner.current,
                            //                    onHeightMeasured = { height ->
                            ////                        heightOfCard = height
                            //                    }
                        )
                    } else {
                        if (state.url != null) {
                            url = state.url!!
                        } else {
                            url = state.hdurl!!
                        }
                        SubcomposeAsyncImage(
                            model = url,
                            loading = {
                                CenteredCircularProgress()
                            },
                            contentDescription = "image",
                            contentScale = ContentScale.Crop, // Optional, adjust as needed
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color(0xFF000000)
                                    )
                                )
                            )
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = state.title.toString(),
                            fontFamily = fonts,
                            color = Color.White,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
            }
        }
    }

}