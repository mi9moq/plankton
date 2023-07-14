package com.example.plancton

import android.app.Application
import com.example.plancton.di.DaggerAppComponent

class PlanctonApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}