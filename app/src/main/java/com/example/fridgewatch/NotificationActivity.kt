package com.example.fridgewatch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class NotificationActivity : Activity() {
    
    private lateinit var homeIcon: ImageView
    private lateinit var bellIcon: ImageView
    private lateinit var menuIcon: ImageView
    private lateinit var editIcon: ImageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        homeIcon = findViewById(R.id.home_icon)
        bellIcon = findViewById(R.id.bell_icon)
        menuIcon = findViewById(R.id.menu_icon)
        editIcon = findViewById(R.id.edit_icon)
    }
    
    private fun setupClickListeners() {
        // Home icon click - navigate to Dashboard
        homeIcon.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        // Bell icon click - stay on current activity (already on notification)
        bellIcon.setOnClickListener {
            Toast.makeText(this, "Already on Notifications", Toast.LENGTH_SHORT).show()
        }
        
        // Menu icon click - navigate to Menu
        menuIcon.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        // Edit icon click - placeholder for edit functionality
        editIcon.setOnClickListener {
            Toast.makeText(this, "Edit notifications", Toast.LENGTH_SHORT).show()
        }
    }
}