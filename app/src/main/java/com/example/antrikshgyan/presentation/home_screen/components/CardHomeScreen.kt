package com.example.antrikshgyan.presentation.home_screen.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.antrikshgyan.R
import com.example.antrikshgyan.ui.theme.Blue
import com.example.antrikshgyan.ui.theme.DarkBlue
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun CardHomeScreen(
    heading: String,
    image: Int,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Column(
        modifier = Modifier
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Card(
            modifier = modifier
                .size(170.dp)
                .border(
                    width = 0.4.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            Color.White,
                            DarkBlue
                        )
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color.Transparent)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = DarkBlue,
                    spotColor = DarkBlue
                ),
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(8.dp),

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
                    .background(color = Color.White)
                    .clip(shape = RoundedCornerShape(8.dp))
            ) {

                Image(
                    painter = painterResource(id = image),
                    contentDescription = "spacecraft",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Transparent)
                )
            }


        }

        Text(
            text = heading,
            fontFamily = fonts,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.W300,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                    Color.White,
                    Color(color = 0xFF0858D1),
                    Color(color = 0xFF003F9E)
                ),
                    tileMode = TileMode.Mirror
                )),
            modifier = Modifier.padding(4.dp)
//                .background(
//                brush = Brush.horizontalGradient(listOf(
//                    Color.White,
//                    Color.Blue
//                ))
            )


    }
}