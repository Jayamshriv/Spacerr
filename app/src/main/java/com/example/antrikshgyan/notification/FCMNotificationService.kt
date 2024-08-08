package com.example.antrikshgyan.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigPictureStyle
import com.example.antrikshgyan.MainActivity
import com.example.antrikshgyan.R
import com.example.antrikshgyan.ui.theme.app_color
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.net.URL

class FCMNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val tokenMap = hashMapOf<String,String>()
        tokenMap.put("token", token)
        Log.d("TAG",token)
        Firebase.firestore.collection("device_token").document().set(tokenMap)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("TAG", "From: ${remoteMessage.notification}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d("TAG", "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let {
            Log.d("TAG", "Message Notification Body: ${it.body}")
            Log.d("TAG", "Message Notification imageurl: ${it.imageUrl}")
            Log.d("TAG", "Message Notification media_type: ${it}")
            it.body?.let {
                body -> sendNotification(body, remoteMessage.notification, remoteMessage.data)
            }
        }
    }

    private fun handleNow(data: Map<String, String>) {
        val key1 = data["title"]
        val key2 = data["body"]
    }

    private fun sendNotification(
        messageBody: String,
        data: RemoteMessage.Notification?,
        dataImage: MutableMap<String, String>
    ) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("navigateTo", "APODScreen")
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        var imageUrl=""
        var body = ""
        val title = data?.title ?: "Title"
        val bodyR = data?.body ?: "Body"
        val image = data?.imageUrl ?: "Image"
        if (isImageFormat(image.toString())){
            imageUrl = image.toString()
            body = bodyR
        }else{
            val videoId = imageUrl.substringAfter("embed/")?.substringBefore("?rel")
            imageUrl = "https://i.ytimg.com/vi/$videoId/mqdefault.jpg"
            body = bodyR.subSequence(0,20).toString()
        }

        Log.d("TAG","title : $title body : $body image $imageUrl ")
        val channelId = "default_channel_id"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


        val bigPictureStyle = BigPictureStyle()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                bigPictureStyle
                .bigPicture(getBitmapFromUrl(imageUrl))
                .bigLargeIcon(getBitmapFromDrawable(R.drawable.spacerr__app))
        } else {
            bigPictureStyle
                .bigPicture(getBitmapFromUrl(imageUrl))
                .bigLargeIcon(getBitmapFromDrawable(R.drawable.spacerr__app))
        }


        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_stat_spacerr)
            .setColor(app_color.toArgb())
            .setAutoCancel(false)
            .setLargeIcon(getBitmapFromDrawable(R.drawable.spacerr__app))
            .setStyle(bigPictureStyle)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, "apod_channel", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun getBitmapFromUrl(imageUrl: String): Bitmap? {
        return try {
            val url = URL(imageUrl)
            Log.e("TAG", url.toString())
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            Log.e("TAG", e.message.toString())
            null
        }
    }

    private fun getBitmapFromDrawable(
        @DrawableRes image : Int
    ): Bitmap {
        return BitmapFactory.decodeResource(resources, image)
    }

    private fun isImageFormat(imageUrl : String): Boolean {
        return imageUrl.startsWith("https://apod.nasa.gov/apod/image", ignoreCase = false)
    }
}