package com.krkcoders.appsjar.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.krkcoders.appsjar.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        FirebaseMessaging.getInstance().subscribeToTopic("global")
    }
}