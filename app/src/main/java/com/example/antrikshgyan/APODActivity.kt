package com.example.antrikshgyan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.common.FullScreenImage
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.nasa.components.YoutubePlayer
import com.example.antrikshgyan.presentation.nasa.viewmodel.APODViewModel
import com.example.antrikshgyan.presentation.navgraph.NavGraph
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.AntrikshGyanTheme
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

private val TAG = "APODActivity"

class APODActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        val media = intent.getStringExtra("media")
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )
        setContent {
            AntrikshGyanTheme {
                navController = rememberNavController()
                ApodScreen(
                    title!!,
                    body!!,
                    media!!
                ) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApodScreen(
    title: String,
    body: String,
    media: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBackground())
    ) {
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
                        heading = title,
                        scrollBehavior = scrollBehavior
                    ) {
                        onBackClick()
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
                    Log.e(TAG, title.toString())
                    Text(
                        text = title,
                        fontFamily = fonts,
                        color = Color.White,
                        fontSize = 18.sp,
                        lineHeight = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    if (!media.startsWith("https://apod.nasa.gov/apod/image", ignoreCase = false)) {
                        val videoId = media.substringAfter("embed/").substringBefore("?rel")
                        heightOfCard = 200.dp
                        YoutubePlayer(
                            modifier = Modifier
                                .fillMaxSize(),
                            lifecycleOwner = LocalLifecycleOwner.current,
                            youtubeVideoId = videoId,
                        )
                    } else {
                        if (showImageDialog) {
                            FullScreenImage(url = media) {
                                showImageDialog = false
                            }
                        }
                        SubcomposeAsyncImage(
                            model = media,
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
                        }

                        Text(
                            text = body,
                            fontFamily = fonts,
                            color = Color.White,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Light,
                            style = MaterialTheme.typography.bodySmall
                        )

                    }
                }
            }
        }
    }
}

