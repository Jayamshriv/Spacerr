package com.example.antrikshgyan.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.RingtoneManager
import android.util.Log
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigPictureStyle
import androidx.core.content.res.ResourcesCompat
import com.example.antrikshgyan.MainActivity
import com.example.antrikshgyan.R
import com.example.antrikshgyan.ui.theme.Blue
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

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("TAG", "Message data payload: ${remoteMessage.data}")
//            handleNow(remoteMessage.data)
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d("TAG", "Message Notification Body: ${it.body}")
            Log.d("TAG", "Message Notification imageurl: ${it.imageUrl}")
            it.body?.let {
                body -> sendNotification(body, remoteMessage.notification, remoteMessage.data)
            }
        }
    }

    private fun handleNow(data: Map<String, String>) {
        // Handle the data message here.
        // For example, extract data and take appropriate action.
        val key1 = data["title"]
        val key2 = data["body"]
        // Handle the extracted data
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
        val title = data?.title ?: "Title"
        val body = data?.body ?: "Body"
        val image = data?.imageUrl ?: "Image"
        if (image.equals("image")){
            imageUrl = image.toString()
        }

        Log.d("TAG","title : $title body : $body image $imageUrl ")
        val channelId = "default_channel_id"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val bigPictureStyle = BigPictureStyle()
            .bigPicture(getBitmapFromUrl(imageUrl)
        ).bigLargeIcon(getBitmapFromUrl(imageUrl))

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.spacerr__app)
            .setContentTitle(title)
            .setContentText(body.subSequence(0,60))
            .setAutoCancel(true)
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
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            null
        }
    }
}