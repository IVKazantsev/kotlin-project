package com.kazancev.androiddevelopmentproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)
        val navController = NavHostFragment.findNavController(this)

        val sharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)

        if (sharedPreferences.getString("username", "null") !== "null")
        {
            if (sharedPreferences.getBoolean("autoLogin", false))
            {
                navController.navigate(R.id.firstFragment)
            }
            else
            {
                navController.navigate(R.id.loginFragment)
            }
        }
        else
        {
            navController.navigate(R.id.registerFragment)
        }

        return root
    }
}
