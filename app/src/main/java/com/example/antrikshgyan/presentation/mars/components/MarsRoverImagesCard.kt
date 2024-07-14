package com.example.antrikshgyan.presentation.mars.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.antrikshgyan.domain.model.nasa.mars.PhotoModel
import com.example.antrikshgyan.presentation.common.InfoItem
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun MarsRoverImagesCard(
    modifier: Modifier = Modifier,
    photoModel: PhotoModel?
) {
    val animatable = remember {
        Animatable(0.75f)
    }
    LaunchedEffect(key1 = true) {
        animatable.animateTo(1f, tween(300, easing = FastOutLinearInEasing))
    }
    val TAG = "MarsRoverImagesCard"
    if (photoModel != null) {
        Card(
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .graphicsLayer {
                    this.scaleX = animatable.value
                    this.scaleY = animatable.value
                }

        ) {
            Box{

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

                    Row{
                        InfoItem(heading = "Earth Date", value = photoModel.earth_date, headingFontSize = 12.sp, valueFontSize = 12.sp)
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SubcomposeAsyncImage(
                        model = photoModel.img_src,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(48.dp))
                            }
                        },
                        contentDescription = "image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
//                        .wrapContentWidth()
//                        .wrapContentHeight()
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(
                                shape = RoundedCornerShape(12.dp),
                                color = Color.Transparent
                            )
                            .border(
                                BorderStroke(
                                    0.4.dp, color = Purple
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
//                        .shadow(
//                            elevation = 16.dp,
//                            shape = RoundedCornerShape(12.dp),
//                            clip = true,
//                            ambientColor = Purple,
//                            spotColor = Purple
//                        )

                    )
                }
            }

        }
    } else {
        Log.e(TAG, photoModel.toString())
    }
}