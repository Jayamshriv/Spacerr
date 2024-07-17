package com.example.antrikshgyan.presentation.nasa.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.nasa.APODModel
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun DailyFactDetailsPartialBottomSheet(
    dailyFactDetails: APODModel?
) {
    val TAG = "dailyFactDetails"
    var heightOfCard by remember {
        mutableStateOf(300.dp)
    }

    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 4.dp)

    ) {
        Log.e(TAG, dailyFactDetails.toString())

        if (dailyFactDetails != null) {
            val apod = dailyFactDetails
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
                SubcomposeAsyncImage(
                    model = url,
                    contentDescription = "image",
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentScale = ContentScale.FillBounds,
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
                )
            }

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
                CenteredCircularProgress(
                    modifier = Modifier.size(48.dp),
                )

        }
    }
}
    
