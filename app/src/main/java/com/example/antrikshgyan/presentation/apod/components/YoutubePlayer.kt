package com.example.antrikshgyan.presentation.apod.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(
    modifier: Modifier,
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
//    onHeightMeasured: (height: Dp) -> Unit
) {

    val localDensity = LocalDensity.current
    var heightOfCardYt by remember {
        mutableStateOf(300.dp)
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxSize()
            .onGloballyPositioned {
                heightOfCardYt = with(localDensity) { it.size.height.toDp() }
//                onHeightMeasured(heightOfCardYt)
            }
            .padding(2.dp),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(youtubeVideoId, 0f)
                    }
                })
            }
        })

}