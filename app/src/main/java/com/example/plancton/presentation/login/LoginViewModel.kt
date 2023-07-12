package com.example.plancton.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.AuthErrorType.HTTP400
import com.example.plancton.core.auth.domain.entity.AuthErrorType.HTTP401
import com.example.plancton.core.auth.domain.entity.AuthErrorType.INTERNET
import com.example.plancton.core.auth.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.core.auth.domain.usecase.LoginUseCase
import com.example.plancton.domain.usecase.GetTokenUseCase
import com.example.plancton.feature.auth.login.presentation.LoginRouter
import com.example.plancton.presentation.login.LoginState.Error
import com.example.plancton.presentation.login.LoginState.Initial
import com.example.plancton.presentation.login.LoginState.Loading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val router: LoginRouter,
) : ViewModel() {

    private val _state = MutableLiveData<LoginState>(Initial)
    val state: LiveData<LoginState> = _state

    private val handleError = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is UnknownHostException,
            is ConnectException,
            is NoRouteToHostException,
            is SocketTimeoutException,
            -> _state.value = Error(INTERNET)

            is HttpException -> {
                when (exception.code()) {
                    400 -> _state.value = Error(HTTP400)

                    401 -> _state.value = Error(HTTP401)

                    else -> _state.value = Error(UNKNOWN)
                }
            }

            else -> _state.value = Error(UNKNOWN)
        }
    }

    //TODO иммитация авторизации; переделать
    fun login(auth: Auth) {
        _state.value = Loading

        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
        val token = getTokenUseCase()

        viewModelScope.launch(handleError) {
            //TODO переделать при появлении api
            delay(1000)

            if (auth.email.contains(emailRegex) && auth.password.isNotBlank()) {

                if (auth.email + auth.password != token)
                    _state.value = Error(HTTP401)
                else {
                    Log.d("LOGIN", loginUseCase(auth))
                    router.openMain()
                }
            } else
                _state.value = Error(HTTP400)
        }
    }
}