package com.example.antrikshgyan.presentation.iss.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.iss.ISSPositionDetailModel
import com.example.antrikshgyan.domain.model.iss.ISSPositionModel
import com.example.antrikshgyan.presentation.iss.state.ISSTLESDataState
import com.example.antrikshgyan.presentation.iss.viewmodel.ISSPositionViewModel
import com.example.antrikshgyan.ui.theme.fonts
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.ComposeMapColorScheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun ISSLocationScreen() {

    val localUriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val issViewModel: ISSPositionViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        issViewModel.getISSPosition("kilometers")
        issViewModel.getISSPositionDetail(
            issViewModel.issPos.issPosition.latitude!!,
            issViewModel.issPos.issPosition.longitude!!
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2500)
            issViewModel.getISSPosition("kilometers")
            issViewModel.getISSPositionDetail(
                issViewModel.issPos.issPosition.latitude!!,
                issViewModel.issPos.issPosition.longitude!!
            )
        }
    }
//    val issLatLng = issViewModel.issPos.issPosition
//    Log.e("TAG10", "$issLatLng")
//    val latLng = LatLng(issLatLng.latitude!!,issLatLng.longitude!!)
//    val lng by remember {
//        mutableStateOf(issLatLng.longitude!!)
//    }
//    Toast.makeText(context, "$issLatLng",Toast.LENGTH_LONG).show()
//    Log.e("TAG11", "1 $latLng")
//    issViewModel.getISSPositionDetail(latLng.latitude,latLng.longitude)
//    val issPositionDetails = issViewModel.issPositionDetail.collectAsState().value.issPositionDetail


//    Log.e("TAG11", "2 $latLng")
    val issPositionDetailModel by issViewModel.issPositionDetail.collectAsState()
    val issTLES by issViewModel.issTLES.collectAsState()
    GMapISS(
        context,
        LatLng(
            issViewModel.issPos.issPosition.latitude!!,
            issViewModel.issPos.issPosition.longitude!!
        ),
        issViewModel.issPos.issPosition,
        issPositionDetailModel.issPositionDetail,
        issTLES
    )
}

@Composable
private fun GMapISS(
    context: Context,
    latLng: LatLng,
    isspositionModel: ISSPositionModel,
    issPositionDetail: ISSPositionDetailModel,
    issTLES: ISSTLESDataState,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 2f)
    }

    LaunchedEffect(isspositionModel) {
        Log.e("ISSLATLAN", "$isspositionModel")
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLng(latLng)
        )
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        cameraPositionState = cameraPositionState,
        mapColorScheme = if (isspositionModel.visibility == "eclipsed") ComposeMapColorScheme.DARK else ComposeMapColorScheme.LIGHT
    ) {

        CustomMapMarker(
            context = context,
            position = cameraPositionState.position.target,
            title = "ISS",
            iconResourceId = R.drawable.isss
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        var fabWidthState by remember { mutableStateOf(0.65f) }
        var expanded by remember { mutableStateOf(false) }
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            onClick = { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(450, 450)) togetherWith fadeOut(animationSpec = tween(450)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        IntSize(Int.MAX_VALUE, initialSize.height) at 400
                                        IntSize(targetSize.width, targetSize.height) at 400
                                        durationMillis = 450
                                    }
                                } else {
                                    keyframes {
                                        IntSize(targetSize.width, targetSize.height) at 400
                                        IntSize(initialSize.width, initialSize.height) at 400
                                        durationMillis = 450
                                    }
                                }
                            }
                }, label = ""
            ) { targetExpanded ->

                if (targetExpanded) {
                    ISSDetailScreen(
                        isspositionModel,
                        issPositionDetail,
                        issTLES
                    ) {
                        expanded = !expanded
                        fabWidthState = 0.65f
                    }
                } else {
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .fillMaxWidth(fabWidthState)
                            .align(Alignment.Center)
                            .padding(24.dp),
                        expanded = true,
                        text = {
                            Text(
                                text = "What is ISS?",
                                color = Color.White,
                                fontFamily = fonts,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Info, contentDescription = null,
                                tint = Color.White
                            )
                        },
                        onClick = {
                            Log.e("TAG12", "4 $latLng")
                            expanded = !expanded
                            fabWidthState = 1f
//                            Toast.makeText(context, "3 $isspositionModel", Toast.LENGTH_LONG).show()
                        },
                        containerColor = if (isspositionModel.visibility == "eclipsed") Color(
                            0xFF3F4E4F
                        ) else Color(0xFF1CA6AF)
                    )
                }

            }
        }
    }
}