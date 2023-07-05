package com.example.plancton.presentation.entry

import androidx.lifecycle.ViewModel
import com.example.plancton.navigation.router.EntryRouter
import javax.inject.Inject

class EntryViewModel @Inject constructor(
    private val router: EntryRouter,
) : ViewModel() {

    fun openLogin() {
        router.openLogin()
    }

    fun openRegistration() {
        router.openRegistration()
    }
}