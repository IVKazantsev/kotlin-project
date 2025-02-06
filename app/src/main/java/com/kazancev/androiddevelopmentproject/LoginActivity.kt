package com.kazancev.androiddevelopmentproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var autoLoginCheckbox: CheckBox
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        autoLoginCheckbox = findViewById(R.id.rememberMeCheckbox)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener { onLoginButtonClick() }
    }

    private fun onLoginButtonClick() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val enteredUsername = usernameInput.text.toString()
        val enteredPassword = passwordInput.text.toString()

        if (enteredUsername == savedUsername && enteredPassword == savedPassword) {
            with(sharedPreferences.edit()) {
                putBoolean("auto_login", autoLoginCheckbox.isChecked)
                apply()
            }
            startActivity(Intent(this, ContentActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Неверные данные для входа", Toast.LENGTH_SHORT).show()
        }
    }
}

