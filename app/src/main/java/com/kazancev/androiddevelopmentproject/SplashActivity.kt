package com.kazancev.androiddevelopmentproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val progressBar: ProgressBar = findViewById(R.id.indeterminateBar)
        progressBar.visibility = ProgressBar.VISIBLE

        Handler().postDelayed({
            checkUserSession()
        }, 2000)
    }

    private fun checkUserSession() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val emailOrPhone = sharedPreferences.getString("username", null)
        val password = sharedPreferences.getString("password", null)
        val autoLogin = sharedPreferences.getBoolean("auto_login", false)

        when {
            emailOrPhone != null && password != null && autoLogin -> {
                startActivity(Intent(this, ContentActivity::class.java))
            }
            emailOrPhone != null && password != null -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else -> {
                startActivity(Intent(this, RegistrationActivity::class.java))
            }
        }
        finish()
    }
}