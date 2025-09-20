package com.example.fridgewatch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class AboutUsActivity : Activity() {
    
    private lateinit var homeIcon: ImageView
    private lateinit var bellIcon: ImageView
    private lateinit var menuIcon: ImageView
    private lateinit var backButton: ImageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        homeIcon = findViewById(R.id.home_icon)
        bellIcon = findViewById(R.id.bell_icon)
        menuIcon = findViewById(R.id.menu_icon)
        backButton = findViewById(R.id.back_button)
    }
    
    private fun setupClickListeners() {
        // Back button click - go back to previous activity
        backButton.setOnClickListener {
            finish()
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
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}