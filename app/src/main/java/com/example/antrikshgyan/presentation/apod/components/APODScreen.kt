package com.example.antrikshgyan.presentation.apod.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APODScreen(
    heading : String,
    apodModel: APODModel,
    navController: NavController
) {

    val TAG = "APODScreen"
    val apod = apodModel
    var revealContent by remember { mutableStateOf(false) }


    Box {
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

            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .padding(innerPadding)

            ) {
                Log.e(TAG, apod.toString())
                Text(
                    text = apod.title!!,
                    fontFamily = fonts,
                    color = Color.White,
                    fontSize = 18.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge
                )



                LaunchedEffect(key1 = Unit) {
                    delay(1000)
                    revealContent = true
                }

                if (revealContent) {

                    Image(
                        painter = rememberAsyncImagePainter(model = apod.hdurl),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(vertical = 8.dp)
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.White
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
                    )

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