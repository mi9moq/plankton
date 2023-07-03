package com.example.plancton.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plancton.PlanctonApp
import com.example.plancton.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as PlanctonApp).component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}