package com.kazancev.androiddevelopmentproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment()
{
    private var usernameInput: EditText? = null
    private var passwordInput: EditText? = null
    private var autoLoginCheckbox: CheckBox? = null
    private var loginButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        usernameInput = view.findViewById(R.id.usernameInput)
        passwordInput = view.findViewById(R.id.passwordInput)
        autoLoginCheckbox = view.findViewById(R.id.rememberMeCheckbox)
        loginButton = view.findViewById(R.id.loginButton)

        loginButton?.setOnClickListener { onLoginButtonClick() }

        return view
    }

    private fun onLoginButtonClick()
    {
        val sharedPreferences: SharedPreferences? = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        if (sharedPreferences != null)
        {
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
            navigateToContent()
        }
    }

    @Throws(Exception::class)
    private fun validate(sharedPreferences: SharedPreferences)
    {
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val enteredUsername = usernameInput?.text.toString()
        val enteredPassword = passwordInput?.text.toString()

        if (enteredUsername != savedUsername || enteredPassword != savedPassword) {
            throw Exception(getString(R.string.invalid_login_data))
        }
    }

    private fun showInvalidDataToast(message: String?)
    {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun saveAutoLogin(sharedPreferences: SharedPreferences)
    {
        val editor = sharedPreferences.edit()
        val autoLogin = autoLoginCheckbox?.isChecked ?: false
        editor.putBoolean("auto_login", autoLogin)
        editor.apply()
    }

    private fun navigateToContent()
    {
        findNavController().navigate(R.id.firstFragment)
    }
}
