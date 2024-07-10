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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.antrikshgyan.domain.model.isro.ISROSpaceCraftModel
import com.example.antrikshgyan.presentation.common.InfoFoot
import com.example.antrikshgyan.presentation.common.InfoHeading
import com.example.antrikshgyan.presentation.common.InfoItem
import com.example.antrikshgyan.ui.theme.FailureRed
import com.example.antrikshgyan.ui.theme.SuccessGreen

@Preview
@Composable
fun ISROSpacecraft(
    modifier: Modifier = Modifier,
    spacecraft : ISROSpaceCraftModel = ISROSpaceCraftModel()
) {

    val localUriHandler = LocalUriHandler.current
    val animatable = remember {
        Animatable(0.75f)
    }
    LaunchedEffect(key1 = true) {
        animatable.animateTo(1f, tween(300, easing = FastOutLinearInEasing))
    }
    val successSweepGradientColor = listOf(
        Color(0xFF08922F),
        Color(0xFF2CE95B),
        Color(0xFF32E20A),
        Color(0xFF5FE441),
    )
    val failureSweepGradientColor = listOf(
        Color(0xFFA32121),
        Color(0xFFCE1717),
        Color(0xFFDB3535),
        Color(0xFFFA5858),
        )

    val status: Boolean = if (spacecraft.missionStatus == "MISSION SUCCESSFUL") true else false

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            width = 0.5.dp,
            brush = Brush.sweepGradient(
                colors = if (status) successSweepGradientColor else failureSweepGradientColor,
            )
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .graphicsLayer{
                this.scaleX = animatable.value
                this.scaleY = animatable.value
            }.shadow(
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = if (status) SuccessGreen else FailureRed,
                spotColor = if (status) SuccessGreen else FailureRed,
            )
            .background(shape = RoundedCornerShape(8.dp),
                color =
                Color(0x74424242)
            ).clickable {
                localUriHandler.openUri(spacecraft.link)
            }
    ) {


        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            spacecraft.apply {

            val status: Boolean = if (missionStatus == "MISSION SUCCESSFUL") true else false
            InfoHeading(heading = "Name", value = name )
            InfoItem(heading = "Launch Date ", value = launchDate )
            InfoItem(heading = "Launch Vehicle", value = launchVehicle )
            InfoItem(heading = "Orbit Type", value = orbitType )
            InfoItem(heading = "Application", value =  application)
            InfoFoot(heading = "Status", value = missionStatus , status = status )
            }
//
        }
    }
}



