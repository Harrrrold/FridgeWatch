package com.example.fridgewatch

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.view.View
import android.widget.Toast

class DashboardActivity : Activity() {
    
    private lateinit var statusTextView: TextView
    private lateinit var doorIconImageView: ImageView
    private lateinit var statusIndicatorLayout: LinearLayout
    private lateinit var statusText: TextView
    private lateinit var timerTextView: TextView
    private lateinit var notificationLayout: LinearLayout
    private lateinit var homeIcon: ImageView
    private lateinit var bellIcon: ImageView
    private lateinit var menuIcon: ImageView
    
    private var countDownTimer: CountDownTimer? = null
    private var isOnline = false // Default to offline (red status)
    private var isDoorClosed = true // Default to closed
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        
        initializeViews()
        setupClickListeners()
        // Don't start timer initially since door is closed
    }
    
    private fun initializeViews() {
        // Find views by their IDs (we'll need to add IDs to the layout)
        statusTextView = findViewById(R.id.door_status_text)
        doorIconImageView = findViewById(R.id.door_icon)
        statusIndicatorLayout = findViewById(R.id.status_indicator)
        statusText = findViewById(R.id.status_text)
        timerTextView = findViewById(R.id.timer_text)
        notificationLayout = findViewById(R.id.notification_button)
        homeIcon = findViewById(R.id.home_icon)
        bellIcon = findViewById(R.id.bell_icon)
        menuIcon = findViewById(R.id.menu_icon)
        
        // Set initial status (offline and red)
        updateConnectionStatus(false)
        updateDoorStatus(true)
    }
    
    private fun setupClickListeners() {
        // Notification button click
        notificationLayout.setOnClickListener {
            Toast.makeText(this, "Notification button clicked", Toast.LENGTH_SHORT).show()
        }
        
        // Bottom navigation clicks
        homeIcon.setOnClickListener {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
        }
        
        bellIcon.setOnClickListener {
            Toast.makeText(this, "Bell clicked", Toast.LENGTH_SHORT).show()
        }
        
        menuIcon.setOnClickListener {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
        }
        
        // Status indicator click to toggle online/offline
        statusIndicatorLayout.setOnClickListener {
            isOnline = !isOnline
            updateConnectionStatus(isOnline)
        }
        
        // Door status click to toggle open/closed
        statusTextView.setOnClickListener {
            isDoorClosed = !isDoorClosed
            updateDoorStatus(isDoorClosed)
        }
    }
    
    private fun updateConnectionStatus(online: Boolean) {
        isOnline = online
        if (online) {
            statusText.text = getString(R.string.status_online)
            statusIndicatorLayout.setBackgroundResource(R.drawable.online_status_background)
        } else {
            statusText.text = getString(R.string.status_offline)
            statusIndicatorLayout.setBackgroundResource(R.drawable.offline_status_background)
        }
    }
    
    private fun updateDoorStatus(closed: Boolean) {
        isDoorClosed = closed
        if (closed) {
            statusTextView.text = getString(R.string.door_status_closed)
            statusTextView.setTextColor(getColor(R.color.red))
            doorIconImageView.setColorFilter(getColor(R.color.red))
            // Stop timer when door is closed
            stopTimer()
        } else {
            statusTextView.text = getString(R.string.door_status_open)
            statusTextView.setTextColor(getColor(R.color.light_green))
            doorIconImageView.setColorFilter(getColor(R.color.light_green))
            // Start timer when door is opened
            startTimer()
        }
    }
    
    private fun startTimer() {
        // Cancel any existing timer first
        stopTimer()
        countDownTimer = object : CountDownTimer(120000, 1000) { // 2 minutes
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 60000)
                val seconds = ((millisUntilFinished % 60000) / 1000)
                timerTextView.text = "Timer: ${String.format("%02d", minutes)}:${String.format("%02d", seconds)}:00"
            }
            
            override fun onFinish() {
                timerTextView.text = "Timer: 00:00:00"
            }
        }.start()
    }
    
    private fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        timerTextView.text = "Timer: 00:00:00"
    }
    
    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}