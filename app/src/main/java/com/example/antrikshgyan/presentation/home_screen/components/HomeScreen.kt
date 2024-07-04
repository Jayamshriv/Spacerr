package com.example.antrikshgyan.presentation.home_screen.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.presentation.apod.viewmodel.APODViewModel
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.Typography
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun HomeScreen(
    navController: NavController,
    imageUrl: String = "https://api.nasa.gov/EPIC/archive/natural/2020/09/30/png/epic_1b_20200930001751.png?api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m",
    title : String = "This image was taken by NASA's adjkadjasbd kaskasdbkasbkjd kja dkas kjabdka oihaoid aob EPIC camera onboard the NOAA DSCOVR spacecraft"
) {
   Column(
       modifier = Modifier
           .padding(8.dp)
           .verticalScroll(rememberScrollState())
   ){
       HeadingHomeScreen()
       APODHomeScreen(navController)

   }
}
