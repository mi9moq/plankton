package com.example.plancton.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plancton.PlanctonApp
import com.example.plancton.R
import com.example.plancton.ui.fragment.EventFragment

class MainActivity : AppCompatActivity() {

    val component by lazy {
        (application as PlanctonApp).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EventFragment.newInstanceAddEvent())
                .commit()
        }
    }
}