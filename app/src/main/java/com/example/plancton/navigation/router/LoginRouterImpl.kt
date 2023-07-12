package com.example.plancton.navigation.router

import com.example.plancton.feature.auth.login.presentation.LoginRouter
import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LoginRouterImpl @Inject constructor(
    private val router: Router
): LoginRouter {

    override fun openMain() {
        router.newRootScreen(getMainScreen())
    }
}