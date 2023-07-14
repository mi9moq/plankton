package com.example.plancton.navigation.screen

import com.example.plancton.ui.fragment.EventFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getAddEventScreen() = FragmentScreen {
    EventFragment.newInstanceAddEvent()
}