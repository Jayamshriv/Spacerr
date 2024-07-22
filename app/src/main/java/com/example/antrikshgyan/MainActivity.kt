package com.example.antrikshgyan

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.example.antrikshgyan.presentation.home_screen.components.HomeScreen
import com.example.antrikshgyan.presentation.navgraph.NavGraph
import com.example.antrikshgyan.ui.theme.AntrikshGyanTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalCoilApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onDestroy() {
        super.onDestroy()
        imageLoader.diskCache?.clear()
        imageLoader.memoryCache?.clear()
        Log.e("MainActivity", "Cache Cleared $imageLoader")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )
        setContent {
            AntrikshGyanTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBackground())
                ) {
                    NavGraph()
                }
            }
        }
    }
}

@Composable
fun FullScreenContent() {
    var isSystemUiVisible by remember { mutableStateOf(true) }
    val windowInsetsController = rememberWindowInsetsController()

    LaunchedEffect(isSystemUiVisible) {
        if (isSystemUiVisible) {
            windowInsetsController?.show(WindowInsetsCompat.Type.systemBars())
        } else {
            windowInsetsController?.hide(WindowInsetsCompat.Type.systemBars())
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, dragAmount ->
                    change.consume()
                    if (dragAmount > 0) {
                        isSystemUiVisible = true
                    } else if (dragAmount < 0) {
                        isSystemUiVisible = false
                    }
                }
            }
    ) {
        NavGraph()
    }
}

@Composable
fun rememberWindowInsetsController(): WindowInsetsControllerCompat? {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window
    return remember(window) { window?.decorView?.let { WindowInsetsControllerCompat(window, it) } }
}

@Composable
fun gradientBackground(): Brush {

    val brush = Brush.linearGradient(
        colors = listOf(
//            Color(0xFF4703C7),
//            Color(0xFF161616),
//            Color(0x57EB7D5B),
            Color(0xFF1EC6DB),
//            Color(0x541EC6DB),
            Color(0xFF1B1B1B),
            Color(0xFF272727),
            Color(0xFF1B1B1B),
//            Color(0x575B79EB),
            Color(0x953A5DE2),
//            Color(0x57EB5BA5),
//            Color(0xFF161616),
//            Color(0xFF4703C7),

        ),
//        startX = 0f,
        start = Offset.Zero,
//        endX = Float.POSITIVE_INFINITY
        end = Offset.Infinite
    )
    return brush

}

