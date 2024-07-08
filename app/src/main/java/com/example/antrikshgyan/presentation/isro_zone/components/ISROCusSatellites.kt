package com.example.antrikshgyan.presentation.isro_zone.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.antrikshgyan.data.remote.dto.isro.CustomerSatellite
import com.example.antrikshgyan.data.remote.dto.isro.ISROCusSatellitesDto
import com.example.antrikshgyan.domain.model.CustomerSatelliteModel
import com.example.antrikshgyan.domain.model.ISROLaunchesModel
import com.example.antrikshgyan.presentation.common.InfoFoot
import com.example.antrikshgyan.presentation.common.InfoHeading
import com.example.antrikshgyan.presentation.common.InfoItem
import com.example.antrikshgyan.ui.theme.FailureRed
import com.example.antrikshgyan.ui.theme.SuccessGreen
import kotlin.random.Random

@Composable
fun ISROCusSatellites(
    modifier: Modifier = Modifier,
    customerSatellite : CustomerSatelliteModel
) {
    val animatable = remember {
        Animatable(0.75f)
    }
    LaunchedEffect(key1 = true) {
        animatable.animateTo(1f, tween(300, easing = FastOutLinearInEasing))
    }

    val colorList = listOf(
        Color(0xFF4864FC),
        Color(0xFFFD9036),
        Color(0xFFC35AFC),
        Color(0xFF25DB8C),
        Color(0xFFB1F84A),
    )

    val randomState by remember {
        mutableStateOf(Random.nextInt(0, colorList.size))
    }
//    val random = Random.nextInt(0, colorList.size)

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = 0.65.dp,
                color = colorList[randomState]
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .graphicsLayer {
                this.scaleX = animatable.value
                this.scaleY = animatable.value
            }
            .shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = colorList[randomState],
                spotColor = colorList[randomState]
            )
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color(0x74424242)
            )
    ) {


        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            customerSatellite.apply {

                InfoHeading(heading = "ID", value = id )
                InfoItem(heading = "Country", value = country)
                InfoItem(heading = "Mass", value = mass )
                InfoItem(heading = "Launcher", value =  launcher)
                InfoItem(heading = "Launch Date ", value = launch_date )
            }
//
        }
    }
}