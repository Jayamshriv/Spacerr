package com.example.antrikshgyan.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.antrikshgyan.R
import com.example.antrikshgyan.ui.theme.LightGreen
import com.example.antrikshgyan.ui.theme.Purple
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun HeadingHomeScreen() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 4.dp)
            .padding(horizontal = 16.dp)
        ,
        verticalAlignment = Alignment.Top,
       horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier

        ) {
            Text(
                text = "Welcome,",
                fontFamily = fonts,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Spacer",
                fontFamily = fonts,
                color = Color.White,
                fontSize = 24.sp,
                style = MaterialTheme.typography.bodyLarge
            )

//        HorizontalDivider(
//            Modifier
//                .padding(horizontal = 4.dp, vertical = 2.dp)
//                .height(2.dp)
//                .fillMaxWidth()
//                .background(
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(
//                            Color(0xFF868686),
//                            Color.White
//                        )
//                    )
//                )
//        )
        }

        Image(
            painter = painterResource(id = R.drawable.astronaut_img),
            contentDescription = "as",
            modifier = Modifier
                .size(85.dp)
                .aspectRatio(1f)
        )
    }

}