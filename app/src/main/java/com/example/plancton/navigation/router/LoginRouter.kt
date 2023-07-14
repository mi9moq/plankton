package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getMainScreen
import com.example.plancton.navigation.screen.getRegistrationScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface LoginRouter {

    fun openMain()

    fun openSignup()
}

class LoginRouterImpl @Inject constructor(
    private val router: Router
) : LoginRouter {

    override fun openMain() {
        router.newRootScreen(getMainScreen())
    }

    override fun openSignup() {
        router.navigateTo(getRegistrationScreen())
    }
}