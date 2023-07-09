package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface LoginRouter {

    fun openMain()
}

class LoginRouterImpl @Inject constructor(
    private val router: Router
): LoginRouter {

    override fun openMain() {
        router.newRootScreen(getMainScreen())
    }
}