package com.example.project_innogeeks

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.core.view.WindowCompat

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_inro)
        window.decorView.apply {
            systemUiVisibility=(
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }

        Handler().postDelayed(
            Runnable {
                    val intent = Intent(this@LoginIntro, LogIn::class.java)
                    startActivity(intent)
                    finish()

            },2000)

    }
}