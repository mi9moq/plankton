package com.example.plancton.presentation

import androidx.lifecycle.ViewModel
import com.example.plancton.core.token.domain.usecase.GetTokenUseCase
import com.example.plancton.navigation.router.ActivityRouter
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val router: ActivityRouter,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    fun navigate() {
        val token = getTokenUseCase()
        if (token != null) {
            router.openMain()
        } else {
            router.openLogin()
        }
    }
}