package com.example.cropdigital.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cropdigital.MainActivity
import com.example.cropdigital.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },
            SPLASH_TIME_OUT
        )
    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 3000
    }
}
