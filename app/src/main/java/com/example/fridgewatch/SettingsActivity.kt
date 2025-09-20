package com.example.fridgewatch

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast

class SettingsActivity : Activity() {
    
    private lateinit var homeIcon: ImageView
    private lateinit var bellIcon: ImageView
    private lateinit var menuIcon: ImageView
    private lateinit var backButton: ImageView
    private lateinit var timerInput: EditText
    private lateinit var notificationToggle: Switch
    
    private lateinit var sharedPreferences: SharedPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        initializeViews()
        setupClickListeners()
        loadSettings()
    }
    
    private fun initializeViews() {
        homeIcon = findViewById(R.id.home_icon)
        bellIcon = findViewById(R.id.bell_icon)
        menuIcon = findViewById(R.id.menu_icon)
        backButton = findViewById(R.id.back_button)
        timerInput = findViewById(R.id.timer_input)
        notificationToggle = findViewById(R.id.notification_toggle)
        
        sharedPreferences = getSharedPreferences("FridgeWatchSettings", MODE_PRIVATE)
    }
    
    private fun setupClickListeners() {
        // Back button click - go back to previous activity
        backButton.setOnClickListener {
            saveSettings()
            finish()
        }
        
        // Timer input change listener
        timerInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                // Validate and format the time input
                val timeText = s.toString()
                if (timeText.length == 8 && timeText.matches(Regex("\\d{2}:\\d{2}:\\d{2}"))) {
                    // Valid time format, save it
                    saveSettings()
                }
            }
        })
        
        // Notification toggle change listener
        notificationToggle.setOnCheckedChangeListener { _, isChecked ->
            saveSettings()
            val message = if (isChecked) "Notification alerts enabled" else "Notification alerts disabled"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        
        // Bottom navigation clicks
        homeIcon.setOnClickListener {
            saveSettings()
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        bellIcon.setOnClickListener {
            saveSettings()
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
            finish()
        }
        
        menuIcon.setOnClickListener {
            saveSettings()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    
    private fun loadSettings() {
        // Load saved timer duration
        val savedTimer = sharedPreferences.getString("timer_duration", "00:00:00")
        timerInput.setText(savedTimer)
        
        // Load saved notification setting
        val notificationEnabled = sharedPreferences.getBoolean("notification_enabled", true)
        notificationToggle.isChecked = notificationEnabled
    }
    
    private fun saveSettings() {
        val editor = sharedPreferences.edit()
        
        // Save timer duration
        val timerText = timerInput.text.toString()
        if (timerText.isNotEmpty()) {
            editor.putString("timer_duration", timerText)
        }
        
        // Save notification setting
        editor.putBoolean("notification_enabled", notificationToggle.isChecked)
        
        editor.apply()
    }
    
    override fun onBackPressed() {
        saveSettings()
        super.onBackPressed()
    }
}