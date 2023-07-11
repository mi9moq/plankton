package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface RegisterRouter {

    fun openMain()
}

class RegisterRouterImpl @Inject constructor(
    private val router: Router
): RegisterRouter{

    override fun openMain() {
        router.newRootScreen(getMainScreen())
    }
}