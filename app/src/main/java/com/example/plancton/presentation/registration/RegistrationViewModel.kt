package com.example.plancton.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.domain.entity.AuthErrorType
import com.example.plancton.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.domain.entity.RegistrationRequest
import com.example.plancton.domain.usecase.RegisterUseCase
import com.example.plancton.presentation.registration.RegistrationState.Error
import com.example.plancton.presentation.registration.RegistrationState.Initial
import com.example.plancton.presentation.registration.RegistrationState.Loading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<RegistrationState>(Initial)
    val state: LiveData<RegistrationState> = _state

    private val handleError = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is UnknownHostException,
            is ConnectException,
            is NoRouteToHostException,
            is SocketTimeoutException,
            -> _state.value = Error(AuthErrorType.INTERNET)

            is HttpException -> {
                when (exception.code()) {
                    400 -> _state.value = Error(AuthErrorType.HTTP400)

                    else -> _state.value = Error(UNKNOWN)
                }
            }

            else -> _state.value = Error(UNKNOWN)
        }
    }

    fun register(registrationRequest: RegistrationRequest) {
        _state.value = Loading

        viewModelScope.launch(handleError) {
            //TODO избавиться от delay
            delay(1000)
            registerUseCase(registrationRequest)
            //throw ConnectException()
            //throw HttpException(Response.error<Any>(400, "s".toResponseBody()))
            //throw HttpException(Response.error<Any>(401, "s".toResponseBody()))
            //throw NullPointerException()
        }
    }
}