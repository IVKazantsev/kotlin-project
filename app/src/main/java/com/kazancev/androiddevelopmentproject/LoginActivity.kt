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

class LoginActivity : AppCompatActivity()
{
    private var usernameInput: EditText? = null
    private var passwordInput: EditText? = null
    private var autoLoginCheckbox: CheckBox? = null
    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        autoLoginCheckbox = findViewById(R.id.rememberMeCheckbox)
        loginButton = findViewById(R.id.loginButton)

        loginButton?.setOnClickListener { onLoginButtonClick() }
    }

    private fun onLoginButtonClick()
    {
        val sharedPreferences: SharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)
        try
        {
            validate(sharedPreferences)
        }
        catch (e: Exception)
        {
            showInvalidDataToast(e.message)

            return
        }

        saveAutoLogin(sharedPreferences)

        navigateToContentActivity()
    }

    @Throws(Exception::class)
    private fun validate(sharedPreferences: SharedPreferences)
    {
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val enteredUsername = usernameInput?.text.toString()
        val enteredPassword = passwordInput?.text.toString()

        if (enteredUsername != savedUsername || enteredPassword != savedPassword)
        {
            throw Exception(getString(R.string.invalid_login_data))
        }
    }

    private fun showInvalidDataToast(message: String?)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun saveAutoLogin(sharedPreferences: SharedPreferences)
    {
        val editor = sharedPreferences.edit()

        val autoLogin = autoLoginCheckbox?.isChecked ?: false
        editor.putBoolean("auto_login", autoLogin)

        editor.apply()
    }

    private fun navigateToContentActivity()
    {
        val intent = Intent(this, ContentActivity::class.java)
        startActivity(intent)

        finish()
    }
}
