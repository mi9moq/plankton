package com.example.plancton

import android.app.Application
import androidx.savedstate.SavedStateRegistryOwner
import com.example.plancton.di.DaggerAppComponent
import com.example.plancton.feature.auth.login.di.component.LoginComponent
import com.example.plancton.feature.auth.login.di.component.LoginComponentOwner

class PlanctonApp : Application(), LoginComponentOwner {

    private var loginComponent: LoginComponent? = null

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getLoginComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoginComponent {
        if (loginComponent == null) {
            loginComponent = component.loginComponentFactory.create(
                this,
                savedStateRegistryOwner
            )
        }
        return loginComponent!!
    }

    override fun clearLoginComponent() {
        loginComponent = null
    }
}