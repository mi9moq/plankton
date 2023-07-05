package com.example.plancton.navigation.screen

import com.example.plancton.ui.fragment.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getMainScreen() = FragmentScreen {
    MainFragment.newInstance()
}