package com.example.antrikshgyan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.antrikshgyan.presentation.home_screen.components.HomeScreen
import com.example.antrikshgyan.presentation.navgraph.NavGraph
import com.example.antrikshgyan.ui.theme.AntrikshGyanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntrikshGyanTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBackground())) {
                   NavGraph()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AntrikshGyanTheme {
        Greeting("Android")
    }
}

@Composable
fun gradientBackground(): Brush {

    val brush = Brush.linearGradient(
        colors = listOf(
//            Color(0xFF4703C7),
//            Color(0xFF161616),
//            Color(0x57EB7D5B),
            Color(0xFF1EC6DB),
            Color(0xFF1B1B1B),
            Color(0xFF272727),
            Color(0xFF1B1B1B),
            Color(0x575B79EB),
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

