package com.example.antrikshgyan.presentation.insight3d

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.antrikshgyan.R


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun Insight3DScreen(){

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.astars_bg),
            contentDescription =null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.15f),
            contentScale = ContentScale.FillBounds
        )

        Scaffold {innerPadding->

            WebViewPage(url = "https://eyes.nasa.gov/apps/orrery/#/inner_solar_system", Modifier.padding(innerPadding))

        }
    }

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(url: String, modifier: Modifier){

    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null

    AndroidView(
        modifier = modifier,
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                webViewClient = WebViewClient()

                settings.javaScriptEnabled = true

                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnabled = view.canGoBack()
                    }
                }
                loadUrl(url)
                webView = this
            }
        }, update = {
            webView = it
        })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}
