package com.example.fridgewatch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class MenuActivity : Activity() {
    
    private lateinit var homeIcon: ImageView
    private lateinit var bellIcon: ImageView
    private lateinit var menuIcon: ImageView
    private lateinit var backButton: ImageView
    private lateinit var settingsButton: LinearLayout
    private lateinit var aboutUsButton: LinearLayout
    private lateinit var termsButton: LinearLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        homeIcon = findViewById(R.id.home_icon)
        bellIcon = findViewById(R.id.bell_icon)
        menuIcon = findViewById(R.id.menu_icon)
        backButton = findViewById(R.id.back_button)
        settingsButton = findViewById(R.id.settings_button)
        aboutUsButton = findViewById(R.id.about_us_button)
        termsButton = findViewById(R.id.terms_button)
    }
    
    private fun setupClickListeners() {
        // Back button click - go back to previous activity
        backButton.setOnClickListener {
            finish()
        }
        
        // Settings button click - navigate to Settings
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        
        // About Us button click - navigate to About Us
        aboutUsButton.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
        
        // Terms and Agreement button click - navigate to Terms
        termsButton.setOnClickListener {
            val intent = Intent(this, TermsAndAgreementActivity::class.java)
            startActivity(intent)
        }
        
        // Bottom navigation clicks
        homeIcon.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        bellIcon.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        menuIcon.setOnClickListener {
            Toast.makeText(this, "Already on Menu", Toast.LENGTH_SHORT).show()
        }
    }
}