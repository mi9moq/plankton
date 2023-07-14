package com.example.plancton.navigation.router

import com.example.plancton.navigation.screen.getAddEventScreen
import com.example.plancton.navigation.screen.getEntryScreen
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface MainRouter {

    fun openAddEvent()

    fun openEntry()
}

class MainRouterImpl @Inject constructor(
    private val router: Router
) : MainRouter {

    override fun openAddEvent() {
        router.navigateTo(getAddEventScreen())
    }

    override fun openEntry() {
        router.newRootScreen(getEntryScreen())
    }
}