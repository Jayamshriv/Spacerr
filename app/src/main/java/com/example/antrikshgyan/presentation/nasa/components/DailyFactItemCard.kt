package com.example.antrikshgyan.presentation.nasa.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.domain.model.nasa.APODModel
import com.example.antrikshgyan.ui.theme.LightGreen
import com.example.antrikshgyan.ui.theme.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyFactItemCard(
    item: APODModel
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(
            focusedElevation = 16.dp,
            defaultElevation = 16.dp,
            pressedElevation = 16.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFCDE8E5),
                    Color(0xFFEEF7FF),
                    Color(0xFF7AB2B2),
                    Color(0xFF4D869C),
                )
            )
        ),
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Transparent)
            .shadow(
                clip = true,
                elevation = 0.dp,
                ambientColor = LightGreen,
                spotColor = LightGreen
            )
            .clickable {
                showBottomSheet = true
            }
    ) {
        Row(
            modifier = Modifier
                .padding(6.dp)

        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(
                    text = item.title.toString().trim(),
                    maxLines = 1,
                    fontFamily = fonts,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    lineHeight = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    text = item.explanation.toString().trim(),
                    maxLines = 5,
                    minLines = 3,
                    fontFamily = fonts,
                    fontSize = 12.sp,
                    softWrap = true,
                    textAlign = TextAlign.Justify,
                    lineHeight = 17.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .background(
                        color = Color.Transparent,
                        shape = RectangleShape
                    )
            ){
                if (item.media_type != "video"){
                    Image(
                        painter = rememberAsyncImagePainter(model = item.url),
                        contentDescription = "image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }else{
                    Log.e("DailyFactItemCard", item.url!!)
                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    containerColor =Color(0xFF0B112E),
                    contentColor = Color.White,
                    modifier = Modifier
                        .fillMaxHeight()
                        ,
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    DailyFactDetailsPartialBottomSheet(dailyFactDetails = item)
                }
            }

        }
    }
}
