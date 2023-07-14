package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getAddEventScreen
import com.example.plancton.navigation.screen.getEditUserScreen
import com.example.plancton.navigation.screen.getLoginScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface MainRouter {

    fun openAddEvent()

    fun openLogin()

    fun openEditUser()
}

class MainRouterImpl @Inject constructor(
    private val router: Router,
) : MainRouter {

    override fun openAddEvent() {
        router.navigateTo(getAddEventScreen())
    }

    override fun openLogin() {
        router.newRootScreen(getLoginScreen())
    }

    override fun openEditUser() {
        router.navigateTo(getEditUserScreen())
    }
}