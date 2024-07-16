package com.example.antrikshgyan.presentation.mars.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.nasa.mars.PhotoModel
import com.example.antrikshgyan.presentation.common.CenteredCircularProgress
import com.example.antrikshgyan.presentation.common.InfoFoot
import com.example.antrikshgyan.presentation.common.InfoHeading
import com.example.antrikshgyan.presentation.common.InfoItem
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.FailureRed
import com.example.antrikshgyan.ui.theme.LightLightGreen
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.SuccessGreen
import com.example.antrikshgyan.ui.theme.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsRoverDetailsScreen(
    navController: NavController,
    photoModel: PhotoModel?
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.marrs_night_bg),
            contentDescription = "bg",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.35f),
            contentScale = ContentScale.Crop
        )
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {
                TopAppCustomBar(heading = "Image Details", scrollBehavior = scrollBehavior) {
                    navController.navigate(Routes.MarsRoverScreen.routes)
                }
            },
            containerColor = Color.Transparent,
            contentColor = Color.White,

            ) { innerPadding ->
            if (photoModel != null) {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)

                ) {
                    item {
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
                                .padding(8.dp),
                        ) {
                            SubcomposeAsyncImage(
                                model = photoModel.img_src,
                                loading = { CenteredCircularProgress() },
                                contentDescription = "image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
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
                            )
                        }
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                contentColor = Color.White,
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = Color.Black
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .shadow(
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    ambientColor = LightLightGreen,
                                    spotColor = LightLightGreen,
                                )
                                .background(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x74424242)
                                )

                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                photoModel.apply {
                                    var status = "Active"
                                    var valueColor = SuccessGreen
                                    if (rover.status == "active") {
                                        status = "Active"
                                        valueColor = SuccessGreen
                                    } else {
                                        status = "Not Active"
                                        valueColor = FailureRed
                                    }
                                    InfoHeading(heading = "Name", value = rover.name)
                                    InfoItem(heading = "Earth Date", value = earth_date)
                                    InfoItem(heading = "Sol", value = sol.toString())
                                    InfoItem(heading = "Landing Date", value = rover.landing_date)
                                    InfoItem(heading = "Launch Date", value = rover.launch_date)
                                    InfoItem(
                                        heading = "Status",
                                        value = status,
                                        valueColor = valueColor
                                    )
                                    InfoItem(heading = "Sol", value = sol.toString())
                                    InfoItem(heading = "Camera that Captured", value = camera.name)
                                    InfoItem(heading = "Camera Full Name", value = camera.full_name)
                                    InfoItem(heading = "Max Sol", value = rover.max_sol.toString())
                                    InfoItem(heading = "Max Date", value = rover.max_date)
                                    InfoItem(
                                        heading = "Total Photos",
                                        value = rover.total_photos.toString()
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Cameras Installed",
                            fontFamily = fonts,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    item {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                contentColor = Color.White,
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = LightLightGreen
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0x99424242)
                                )
                                .shadow(
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(8.dp),
                                    ambientColor = Blue,
                                    spotColor = Blue,
                                )

                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                photoModel.rover.cameras.forEach { item ->
                                    InfoItem(
                                        heading = item.name,
                                        value = item.full_name,
                                        headingFontSize = 14.sp,
                                        valueFontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                CenteredCircularProgress()
            }
        }
    }
}