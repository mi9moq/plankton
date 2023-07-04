package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getLoginScreen
import com.example.plancton.navigation.screen.getRegistrationScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class EntryRouterImpl @Inject constructor(
    private val router: Router,
) : EntryRouter {

    override fun openLogin() {
        router.navigateTo(getLoginScreen())
    }

    override fun openRegistration() {
        router.navigateTo(getRegistrationScreen())
    }
}