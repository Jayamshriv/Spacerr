package com.example.antrikshgyan.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.size.Dimension
import com.example.antrikshgyan.ui.theme.fonts

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    heading: String,
    value: String?,
    headingFontSize : TextUnit = 16.sp,
    valueFontSize : TextUnit = 14.sp
) {
    if (!value.isNullOrBlank()) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {

            Text(
                text = "$heading :",
                fontFamily = fonts,
                fontSize = headingFontSize,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = value,
                fontFamily = fonts,
                fontSize = valueFontSize,
                fontWeight = FontWeight.W200,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}