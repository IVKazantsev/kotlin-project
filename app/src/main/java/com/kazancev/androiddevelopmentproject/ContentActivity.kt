package com.kazancev.androiddevelopmentproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity()
{
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottom_nav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id)
            {
                R.id.firstFragment, R.id.secondFragment ->
                {
                    bottom_nav.visibility = View.VISIBLE
                }
                else ->
                {
                    bottom_nav.visibility = View.GONE
                }
            }
        }
    }
}
