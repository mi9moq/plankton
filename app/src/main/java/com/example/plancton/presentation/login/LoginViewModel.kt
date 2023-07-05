package com.example.plancton.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.ErrorType.UNKNOWN
import com.example.plancton.domain.usecase.LoginUseCase
import com.example.plancton.presentation.login.LoginState.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<LoginState>(Initial)
    val state: LiveData<LoginState> = _state

    private val handleError = CoroutineExceptionHandler { _, exception ->
        //TODO добавить обработку разных ошибок
        _state.value = Error(UNKNOWN)
    }

    fun login(auth: Auth) {
        _state.value = Loading

        viewModelScope.launch(handleError) {
            //TODO переделать при появлении api
            delay(3000)
            Log.d("TAG", loginUseCase(auth))
            throw NullPointerException()
        }
    }
}