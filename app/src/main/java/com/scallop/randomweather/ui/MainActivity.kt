package com.scallop.randomweather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.scallop.randomweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        with(mBinding) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(navHostFragment.id) as NavHostFragment

            val appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)
            toolbar.setupWithNavController(navHostFragment.navController, appBarConfiguration)
        }
    }
}