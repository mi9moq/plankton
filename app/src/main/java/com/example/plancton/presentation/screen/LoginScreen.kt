package com.example.plancton.presentation.screen

import com.example.plancton.ui.fragment.LoginFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getLoginScreen(): FragmentScreen = FragmentScreen {
    LoginFragment()
}