package com.example.plancton.navigation.screen

import com.example.plancton.ui.fragment.RegistrationFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getRegistrationScreen(): FragmentScreen = FragmentScreen {
    RegistrationFragment()
}