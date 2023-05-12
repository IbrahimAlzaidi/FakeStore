package com.example.fakestore.ui

import com.example.fakestore.R
import com.example.fakestore.databinding.ActivityMainBinding
import com.example.fakestore.ui.base.BaseActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity() : BaseActivity<ActivityMainBinding>() {

    override val LOG_TAG: String = MainActivity::class.simpleName.toString()
    override fun getLayoutResId() = R.layout.activity_main
    private val navController by lazy { findNavController(R.id.nav_host_fragment_content_main) }
    override fun setup() {
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)
        binding.contentMain.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp()
                || super.onSupportNavigateUp()
    }
}