package com.example.plancton.navigation.screen

import com.example.plancton.ui.fragment.EntryFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getEntryFragment(): FragmentScreen = FragmentScreen {
    EntryFragment()
}