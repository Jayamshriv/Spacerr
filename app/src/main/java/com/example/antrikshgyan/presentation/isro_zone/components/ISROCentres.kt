package com.example.antrikshgyan.presentation.isro_zone.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.antrikshgyan.domain.model.CentreModel
import com.example.antrikshgyan.presentation.common.InfoHeading
import com.example.antrikshgyan.presentation.common.InfoItem
import kotlin.random.Random

@Composable
fun ISROCentres(
    modifier: Modifier = Modifier,
    centre: CentreModel
) {
    val animatable = remember {
        Animatable(0.75f)
    }
    LaunchedEffect(key1 = true) {
        animatable.animateTo(1f, tween(300, easing = FastOutLinearInEasing))
    }

    val colorList = listOf(
        Color(0xFF3F51B5),
        Color(0xFFEF6C00),
        Color(0xFF18FFFF),
        Color(0xFFFF5252),
        Color(0xFF67912B),
    )

    val random = Random.nextInt(0, colorList.size)

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = 0.65.dp,
            color = colorList[random]
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
                ambientColor = colorList[random],
                spotColor = colorList[random]
            )
            .background(
                shape = RoundedCornerShape(8.dp),
                color =
                Color(0x74424242)
            )
    ) {


        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            centre.apply {

                InfoHeading(heading = "Name", value = name )
                InfoItem(heading = "Place", value = Place)
                InfoItem(heading = "State", value = State )

            }
//
        }
    }
}