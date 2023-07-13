package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getEntryScreen
import com.example.plancton.navigation.screen.getMainScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface EventRouter {

    fun back()

    fun  openEntry()
}

class EventRouterImpl @Inject constructor(
    private val router: Router
) : EventRouter {

    override fun back() {
        router.backTo(getMainScreen())
    }

    override fun openEntry() {
        router.newRootScreen(getEntryScreen())
    }
}