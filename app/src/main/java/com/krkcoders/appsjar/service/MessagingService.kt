package com.krkcoders.appsjar.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent



class MessagingService : FirebaseMessagingService() {

    private var broadcaster: LocalBroadcastManager? = null

    override fun onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this)
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage!!.notification != null) {
           var broadcaster = LocalBroadcastManager.getInstance(baseContext)
            val intent = Intent("Message")
            intent.putExtra("Title", remoteMessage.notification.title)
            intent.putExtra("Body", remoteMessage.notification.body)
            broadcaster.sendBroadcast(intent)
        }
    }

    fun subscribeTopic() {
        //todo move subscribe function here from LoginActivity and call this function after sign in
        //FirebaseMessaging.getInstance().subscribeToTopic("global")
    }
}