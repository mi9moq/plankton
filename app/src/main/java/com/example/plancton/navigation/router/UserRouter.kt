package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface UserRouter {

    fun backToMain()
}

class UserRouterImpl @Inject constructor(
    private val router: Router,
) : UserRouter {

    override fun backToMain() {
        router.backTo(getMainScreen())
    }
}