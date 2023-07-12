package com.example.plancton.feature.auth.login.di.component

import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner

interface LoginComponentOwner {

    fun getLoginComponent(savedStateRegistryOwner: SavedStateRegistryOwner): LoginComponent

    fun clearLoginComponent()
}

fun Fragment.getComponent(): LoginComponent {

    try {
        return (requireContext().applicationContext as LoginComponentOwner)
            .getLoginComponent(this)
    } catch (e: Exception) {
        throw (TypeCastException("Application do not own this component"))
    }
}