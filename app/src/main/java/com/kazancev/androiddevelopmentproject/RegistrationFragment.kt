package com.kazancev.androiddevelopmentproject

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment()
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        init(view)
        bindEvents()

        return view
    }

    private fun init(view: View)
    {
        switcherRegisterByNumber = view.findViewById(R.id.switcherRegisterByNumber)
        switcherRegisterByNumber?.setTextColor(activeColor)

        switcherRegisterByEmail = view.findViewById(R.id.switcherRegisterByEmail)
        switcherRegisterByEmail?.setTextColor(inactiveColor)

        usernameInput = view.findViewById(R.id.usernameInput)
        externalFieldInput = view.findViewById(R.id.externalFieldInput)
        passwordInput = view.findViewById(R.id.passwordInput)
        confirmPasswordInput = view.findViewById(R.id.passwordConfirmInput)

        registerButton = view.findViewById(R.id.registerButton)
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
        navigateToContent()
    }

    private fun validate()
    {
        val externalField = externalFieldInput?.text.toString()
        if (externalFieldInput?.inputType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
            validateEmail(externalField)
        } else {
            validatePhone(externalField)
        }
        validatePassword(passwordInput?.text.toString(), confirmPasswordInput?.text.toString())
    }

    private fun validateEmail(email: String)
    {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            throw Exception(getString(R.string.invalid_email))
        }
    }

    private fun validatePhone(phone: String)
    {
        if (!Patterns.PHONE.matcher(phone).matches())
        {
            throw Exception(getString(R.string.invalid_phone))
        }
    }

    private fun validatePassword(password: String, confirmPassword: String)
    {
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
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(
            externalFieldInput?.text.toString(),
            passwordInput?.text.toString(),
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                findNavController().navigate(R.id.firstFragment)
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToContent()
    {
        findNavController().navigate(R.id.firstFragment)
    }

    private fun showInvalidDataToast(message: String?)
    {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
