package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface EventRouter {

    fun back()
}

class EventRouterImpl @Inject constructor(
    private val router: Router
) : EventRouter {

    override fun back() {
        router.backTo(getMainScreen())
    }
}