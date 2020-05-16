package com.example.jawwla.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.jawwla.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val SPLASH_TIME_OUT: Long = 3000 // 1 sec
            Handler().postDelayed({
                // This method will be executed once the timer is over
                // Start your app main activity

                startActivity(Intent(this,EmailPasswordActivity::class.java));

                // close this activity
                finish()
            }, SPLASH_TIME_OUT)
        }
    }
