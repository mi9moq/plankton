package com.example.plancton.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.AuthErrorType.HTTP400
import com.example.plancton.domain.entity.AuthErrorType.HTTP401
import com.example.plancton.domain.entity.AuthErrorType.INTERNET
import com.example.plancton.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.domain.usecase.LoginUseCase
import com.example.plancton.presentation.login.LoginState.Error
import com.example.plancton.presentation.login.LoginState.Initial
import com.example.plancton.presentation.login.LoginState.Loading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
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
                }
            }

            else -> _state.value = Error(UNKNOWN)
        }
    }

    fun login(auth: Auth) {
        _state.value = Loading

        viewModelScope.launch(handleError) {
            //TODO переделать при появлении api
            //delay(1000)
            Log.d("TAG", loginUseCase(auth))
            //throw ConnectException()
            //throw HttpException(Response.error<Any>(400, "s".toResponseBody()))
            //throw HttpException(Response.error<Any>(401, "s".toResponseBody()))
            //throw NullPointerException()
        }
    }
}