package com.example.antrikshgyan.presentation.iss.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.antrikshgyan.R
import com.example.antrikshgyan.domain.model.iss.ISSPositionDetailModel
import com.example.antrikshgyan.domain.model.iss.ISSPositionModel
import com.example.antrikshgyan.presentation.common.InfoItem
import com.example.antrikshgyan.presentation.iss.state.ISSTLESDataState
import com.example.antrikshgyan.ui.theme.fonts

//@Preview(showSystemUi = true)
@Composable
fun ISSDetailScreen(
    isspositionModel: ISSPositionModel,
    issPositionDetail: ISSPositionDetailModel,
    issTLES: ISSTLESDataState,
    onClickInfo: () -> Unit
) {
    val TAG = "ISSDetailScreen"
    LazyColumn(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClickInfo()
            }
            .background(
                color = if (isspositionModel.visibility == "eclipsed") Color(0xFF3F4E4F) else
                    Color(0xFF1CA6AF),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
                ,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "What is ISS?",
                    color = Color.White,
                    fontFamily = fonts,
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp
                )
            }
        }

        item {
            Column {
                Text(
                    text = stringResource(R.string.what_is_iss),
                    color = Color.White,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
                Log.e(TAG, isspositionModel.toString())
                isspositionModel.apply {
                    InfoItem(heading = "Altitude", value = "$altitude $units", headingFontSize = 14.sp )
                    InfoItem(heading = "Velocity", value = velocity.toString() ,headingFontSize = 14.sp)
                    InfoItem(heading = "Latitude", value = latitude.toString() ,headingFontSize = 14.sp)
                    InfoItem(heading = "Longitude", value = longitude.toString() ,headingFontSize = 14.sp)
                    InfoItem(heading = "Visibility", value = visibility.toString() ,headingFontSize = 14.sp)
                }
                Log.e(TAG, issPositionDetail.toString())

                issPositionDetail.apply {
                    InfoItem(heading = "Country Code", value =  if(country_code.isNullOrBlank())"Loading..." else country_code,headingFontSize = 14.sp )
                    InfoItem(heading = "Time Zone ID", value =  if(timezone_id.isNullOrBlank())"Loading..." else timezone_id, headingFontSize = 14.sp )
                }

                Log.e(TAG, issTLES.toString())

                Text(
                    text = issTLES.tles,
                    color = Color.White,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}