package com.kazancev.androiddevelopmentproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
        checkUserSession()
        }, 3000)
    }

    private fun checkUserSession()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)

        val emailOrPhone = sharedPreferences.getString("username", null)
        val password = sharedPreferences.getString("password", null)
        val autoLogin = sharedPreferences.getBoolean("auto_login", false)

        if (emailOrPhone != null && password != null)
        {
            if (autoLogin)
            {
                startActivity(Intent(this, ContentActivity::class.java))
                finish()

                return
            }

            startActivity(Intent(this, LoginActivity::class.java))
            finish()

            return
        }

        startActivity(Intent(this, RegistrationActivity::class.java))
        finish()

        return
    }
}
