package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getLoginScreen
import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface ActivityRouter {

    fun openLogin()

    fun openMain()
}

class ActivityRouterImpl @Inject constructor(
    private val router: Router
) : ActivityRouter {

    override fun openLogin() {
        router.newRootScreen(getLoginScreen())
    }

    override fun openMain() {
        router.newRootScreen(getMainScreen())
    }
}