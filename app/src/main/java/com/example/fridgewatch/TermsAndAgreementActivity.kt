package com.example.fridgewatch

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class TermsAndAgreementActivity : Activity() {
    
    private lateinit var homeIcon: ImageView
    private lateinit var bellIcon: ImageView
    private lateinit var menuIcon: ImageView
    private lateinit var backButton: ImageView
    private lateinit var acceptButton: Button
    
    private lateinit var sharedPreferences: SharedPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_agreement)
        
        initializeViews()
        setupClickListeners()
    }
    
    private fun initializeViews() {
        homeIcon = findViewById(R.id.home_icon)
        bellIcon = findViewById(R.id.bell_icon)
        menuIcon = findViewById(R.id.menu_icon)
        backButton = findViewById(R.id.back_button)
        acceptButton = findViewById(R.id.accept_button)
        
        sharedPreferences = getSharedPreferences("FridgeWatchSettings", MODE_PRIVATE)
    }
    
    private fun setupClickListeners() {
        // Back button click - go back to previous activity
        backButton.setOnClickListener {
            finish()
        }
        
        // Accept button click - mark terms as accepted and navigate to dashboard
        acceptButton.setOnClickListener {
            // Save that terms have been accepted
            val editor = sharedPreferences.edit()
            editor.putBoolean("terms_accepted", true)
            editor.apply()
            
            // Show confirmation message
            Toast.makeText(this, "Terms accepted! Welcome to FridgeWatch!", Toast.LENGTH_SHORT).show()
            
            // Navigate to dashboard
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
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