package com.example.antrikshgyan

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.example.antrikshgyan.presentation.navgraph.NavGraph
import com.example.antrikshgyan.presentation.navgraph.Routes
import com.example.antrikshgyan.ui.theme.AntrikshGyanTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalCoilApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController : NavHostController

    override fun onDestroy() {
        super.onDestroy()
//        imageLoader.diskCache?.clear()
//        imageLoader.memoryCache?.clear()
        Log.e("MainActivity", "Cache Cleared $imageLoader")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic("new_user")
            .addOnCompleteListener { done ->
                if (!done.isSuccessful) {
                    Log.e("TAG", "Error Subscribing " + done.exception.toString())
                }
                Log.e("TAG", "Subscribed to " + done.result.toString())

            }
        FirebaseMessaging.getInstance().subscribeToTopic("apod")
            .addOnCompleteListener { done ->
                if (!done.isSuccessful) {
                    Log.e("TAG", "Error Subscribing " + done.exception.toString())
                }
                Log.e("TAG", "Subscribed to " + done.result.toString())

            }
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.Transparent.toArgb(),
                darkScrim = Color.Transparent.toArgb()
            )
        )
        setContent {
//            checkPremission()
            navController = rememberNavController()
            AntrikshGyanTheme {
                getToken()
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBackground())
                ) {
                    NavGraph()
                }
            }
            handleIntent(intent, navController)
        }
    }

    private fun getToken(){

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result

            Log.d("TAG", token)
//            Toast.makeText(baseContext, "Token : $token", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent, navController)
    }

    private fun handleIntent(intent: Intent, navController: NavController) {
        val navigateTo = intent?.getStringExtra("navigateTo")
        Log.e("TAG", navigateTo.toString())
        navigateTo?.let{ it->
            when(it){
                "APODScreen" ->{
                    navController.navigate(Routes.APODScreen.routes)
                }
            }
        }
    }


}
@Composable
private fun checkPremission(){
    var isPermissionGranted: Boolean by remember {
        mutableStateOf(false)
    }
    val permissionArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    } else {
        arrayOf(Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) {it->
        val permissionGranted = it.values.reduce { acc, b -> acc && b }
        if(permissionGranted){
            isPermissionGranted = true
        }else{
            isPermissionGranted = false
        }
    }

    launcher.launch(permissionArray)
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



