package com.krkcoders.appsjar.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        //todo display message in activity instead of logs
        // title is UserName in this case
        // body is a message content
        if (remoteMessage!!.notification != null) {
            Log.e("message-tag", "Title: " + remoteMessage.notification.title)
            Log.e("message-tag", "Body: " + remoteMessage.notification.body)
        }
    }

    fun subscribeTopic() {
        //todo move subscribe function here from LoginActivity and call this function after sign in
        //FirebaseMessaging.getInstance().subscribeToTopic("global")
    }

    fun sendMessage(message: String) {
        //todo post method call for messaging
        // url: /api/notification/send/all
        // params 1. username 2. message content
    }
}