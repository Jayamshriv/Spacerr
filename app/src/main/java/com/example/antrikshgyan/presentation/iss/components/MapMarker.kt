package com.example.antrikshgyan.presentation.iss.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.geometry.Offset
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun CustomMapMarker(
    context: Context,
    position: LatLng,
    title: String,
    iconResourceId: Int
) {

    val icon = bitmapDescriptorFromVector(
        context, iconResourceId
    )
    val markerState = rememberUpdatedMarkerState(
    position =    position
    )
    if (markerState.position != position){
        Polyline(points = listOf(position,LatLng(0.0,0.0)))
    }

    Log.e("Map","$position")
    Marker(
        state = markerState,
        title = title,
        icon = icon,
        draggable = false,
        snippet = "Hii Nigga",
        alpha = 0.95f,
        onInfoWindowClick = {
            Toast.makeText(context, "Info Clicked", Toast.LENGTH_SHORT).show()
        }
    )
}
@Composable
fun rememberUpdatedMarkerState(position: LatLng): MarkerState =
// This pattern is equivalent to what rememberUpdatedState() does:
// rememberUpdatedState() uses MutableState, we use MarkerState.
// This is more efficient than updating position in an effect,
    // as we avoid an additional recomposition.
    remember {
        MarkerState(position = position)
    }.also {
        it.position = position
    }
fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(
        bm
    )
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}