package com.example.plancton.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.AuthErrorType.WRONG_EMAIL
import com.example.plancton.core.auth.domain.entity.AuthErrorType.WRONG_PASSWORD
import com.example.plancton.core.auth.domain.usecase.LoginUseCase
import com.example.plancton.core.token.domain.usecase.SetTokenUseCase
import com.example.plancton.navigation.router.LoginRouter
import com.example.plancton.presentation.login.LoginState.Error
import com.example.plancton.presentation.login.LoginState.Initial
import com.example.plancton.presentation.login.LoginState.Loading
import com.example.plancton.ui.utils.emailRegex
import com.example.plancton.ui.utils.handleAuthError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val router: LoginRouter,
) : ViewModel() {

    private val _state = MutableLiveData<LoginState>(Initial)
    val state: LiveData<LoginState> = _state

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _state.value = Error(handleAuthError(throwable))
    }

    fun login(inputEmail: String?, inputPassword: String?) {
        val email = parseInput(inputEmail)
        val password = parseInput(inputPassword)

        val fieldsValid = validateInput(email, password)
        if (fieldsValid) {
            val auth = Auth(email, password)
            viewModelScope.launch(handler) {
                _state.value = Loading
                val token = loginUseCase(auth)
                setTokenUseCase(token)
                router.openMain()
            }
        }
    }

    fun resetError() {
        _state.value = Initial
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (!email.contains(emailRegex)) {
            _state.value = Error(WRONG_EMAIL)
            return false
        }
        if (password.length < 4) {
            _state.value = Error(WRONG_PASSWORD)
            return false
        }
        return true
    }

    private fun parseInput(input: String?): String = input?.trim() ?: ""

    fun openSignup() {
        router.openSignup()
    }
}