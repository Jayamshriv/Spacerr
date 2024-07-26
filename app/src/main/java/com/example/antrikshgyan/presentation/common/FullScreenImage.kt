package com.example.antrikshgyan.presentation.common

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.antrikshgyan.R


@Composable
fun FullScreenImage(
    url: String?,
    onClose :() -> Unit
) {

    val TAG = "FullScreenImage"
    AlertDialog(
        onDismissRequest = { onClose() },
        dismissButton = {
            IconButton(
                onClick = {
                    onClose()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close, contentDescription = "close",
                    tint = Color.White
                )
            }
        },
        confirmButton = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
//            .wrapContentWidth()
//            .wrapContentHeight()
            .background(
                color = Color.Transparent,
            ),
        containerColor = Color.Transparent,
        tonalElevation = 120.dp,
        text = {
            var scale by remember {
                mutableStateOf(1f)
            }
            var offset by remember {
                mutableStateOf(Offset.Zero)
            }
            BoxWithConstraints(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                    scale = (scale * zoomChange).coerceIn(1f, 5f)

                    val extraWidth = (scale - 1) * constraints.maxWidth
                    val extraHeight = (scale - 1) * constraints.maxHeight

                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2

                    offset = Offset(
                        x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                        y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY),
                    )
                }
            SubcomposeAsyncImage(
                model = url,
                contentDescription = "image",
                contentScale = ContentScale.FillBounds,
                loading = {
                    CenteredCircularProgress()
                },
                modifier = Modifier
                    .clipToBounds()
                    .graphicsLayer {
                        scaleX = scale;scaleY = scale
                        translationX = offset.x;translationY = offset.y
                    }
                    .transformable(
                        state = state
                    )
            )
        }


        }
    )
}