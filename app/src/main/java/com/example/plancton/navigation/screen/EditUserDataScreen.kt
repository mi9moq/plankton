package com.example.plancton.navigation.screen

import com.example.plancton.ui.fragment.EditUserDataFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getEditUserScreen(): FragmentScreen = FragmentScreen {
    EditUserDataFragment()
}