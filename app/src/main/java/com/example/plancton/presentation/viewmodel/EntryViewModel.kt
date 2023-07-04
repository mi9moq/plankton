package com.example.plancton.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.plancton.presentation.router.EntryRouter
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