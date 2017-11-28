package com.krkcoders.appsjar.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.firebase.messaging.FirebaseMessaging
import com.krkcoders.appsjar.R
import android.content.Intent
import io.realm.Realm


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        FirebaseMessaging.getInstance().subscribeToTopic("global")
        Realm.init(this)


        val singnInButton = findViewById(R.id.email_sign_in_button) as Button
        singnInButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }



}