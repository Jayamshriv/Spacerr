package com.example.antrikshgyan.presentation.apod.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.presentation.apod.viewmodel.APODViewModel
import com.example.antrikshgyan.presentation.common.TopAppCustomBar
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APODScreen(
    apodModel: APODModel,
    navController: NavController
) {

    val TAG = "APODScreen"
    val apod = apodModel
//    Box {
//        Surface {
//            Image(
//                painter = painterResource(id = R.drawable.astars_bg),
//                contentDescription = "bg",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .blur(
//                        radiusX = 4.dp,
//                        radiusY = 4.dp,
//                        edgeTreatment = BlurredEdgeTreatment.Unbounded
//                    ),
//                contentScale = ContentScale.Crop
//            )
//        }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar =
        {
            TopAppCustomBar(
                heading = "Astronomy POD"
            ){
                navController.navigate(Routes.HomeScreen.toString())
            }
        }
    ) { innerPadding ->
//        LaunchedEffect(key1 = "wait"){ delay(timeMillis = 2000) }
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
                modifier = Modifier.padding(vertical = 4.dp),
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


        }
    }
}

//}