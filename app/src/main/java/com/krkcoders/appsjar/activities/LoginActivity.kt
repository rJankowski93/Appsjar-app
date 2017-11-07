package com.krkcoders.appsjar.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.krkcoders.appsjar.R


/**
 * Created by Dominik on 07.11.2017.
 */
class LoginActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//            Log.e("LoginActivity/OnCreate",e.message)
//        }
        setContentView(R.layout.login_activity)

    }
}