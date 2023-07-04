package com.example.plancton.navigation

import com.example.plancton.presentation.router.EntryRouter
import com.example.plancton.presentation.screen.getLoginScreen
import com.example.plancton.presentation.screen.getRegistrationScreen
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