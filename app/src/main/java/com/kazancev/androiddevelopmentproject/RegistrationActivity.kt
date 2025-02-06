package com.kazancev.androiddevelopmentproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity()
{
    private val activeColor = Color.BLUE
    private val inactiveColor = Color.LTGRAY
    private var switcherRegisterByNumber: TextView? = null
    private var switcherRegisterByEmail: TextView? = null
    private var usernameInput: EditText? = null
    private var externalFieldInput: EditText? = null
    private var passwordInput: EditText? = null
    private var confirmPasswordInput: EditText? = null
    private var registerButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        init()
        bindEvents()
    }

    private fun init()
    {
        switcherRegisterByNumber = findViewById(R.id.switcherRegisterByNumber)
        switcherRegisterByNumber?.setTextColor(activeColor)

        switcherRegisterByEmail = findViewById(R.id.switcherRegisterByEmail)
        switcherRegisterByEmail?.setTextColor(inactiveColor)

        usernameInput = findViewById(R.id.usernameInput)
        externalFieldInput = findViewById(R.id.externalFieldInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.passwordConfirmInput)

        registerButton = findViewById(R.id.registerButton)
    }

    private fun bindEvents()
    {
        switcherRegisterByNumber?.setOnClickListener { onSwitcherRegisterByNumberClick() }
        switcherRegisterByEmail?.setOnClickListener { onSwitcherRegisterByEmailClick() }
        registerButton?.setOnClickListener { onRegisterButtonClick() }
    }

    private fun onSwitcherRegisterByNumberClick()
    {
        switcherRegisterByNumber?.setTextColor(activeColor)
        switcherRegisterByEmail?.setTextColor(inactiveColor)
        externalFieldInput?.hint = getString(R.string.phone_input)
        externalFieldInput?.inputType = InputType.TYPE_CLASS_PHONE
    }

    private fun onSwitcherRegisterByEmailClick()
    {
        switcherRegisterByEmail?.setTextColor(activeColor)
        switcherRegisterByNumber?.setTextColor(inactiveColor)
        externalFieldInput?.hint = getString(R.string.email_input)
        externalFieldInput?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    private fun onRegisterButtonClick()
    {
        try
        {
            validate()
        }
        catch (e: Exception)
        {
            showInvalidDataToast(e.message)

            return
        }
        saveUserData()
        navigateToContentActivity()
    }

    @Throws(Exception::class)
    private fun validate()
    {
        val externalField = externalFieldInput?.text.toString()
        if (externalFieldInput?.inputType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        {
            validateEmail(externalField)
        }
        else
        {
            validatePhone(externalField)
        }
        validatePassword(passwordInput?.text.toString(), confirmPasswordInput?.text.toString())
    }

    @Throws(Exception::class)
    private fun validateEmail(email: String)
    {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            throw Exception(getString(R.string.invalid_email))
        }
    }

    @Throws(Exception::class)
    private fun validatePhone(phone: String)
    {
        if (!Patterns.PHONE.matcher(phone).matches())
        {
            throw Exception(getString(R.string.invalid_phone))
        }
    }

    @Throws(Exception::class)
    private fun validatePassword(password: String, confirmPassword: String) {
        if (password.length < 8)
        {
            throw Exception(getString(R.string.invalid_password_length))
        }
        if (password != confirmPassword)
        {
            throw Exception(getString(R.string.passwords_do_not_match))
        }
    }

    private fun saveUserData()
    {
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", usernameInput?.text.toString())
        editor.putString("password", passwordInput?.text.toString())
        editor.apply()
    }

    private fun navigateToContentActivity()
    {
        val intent = Intent(this, ContentActivity::class.java)
        startActivity(intent)
        finish() // Закрываем RegistrationActivity, чтобы не возвращаться назад
    }

    private fun showInvalidDataToast(message: String?)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
