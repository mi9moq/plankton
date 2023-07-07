package com.example.plancton.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.domain.entity.AuthErrorType
import com.example.plancton.domain.entity.AuthErrorType.HTTP400
import com.example.plancton.domain.entity.AuthErrorType.UNKNOWN
import com.example.plancton.domain.entity.RegistrationRequest
import com.example.plancton.domain.usecase.RegisterUseCase
import com.example.plancton.domain.usecase.SetTokenUseCase
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
    private val setTokenUseCase: SetTokenUseCase,
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
                    400 -> _state.value = Error(HTTP400)

                    else -> _state.value = Error(UNKNOWN)
                }
            }

            else -> _state.value = Error(UNKNOWN)
        }
    }

    //TODO иммитация работы с сервером; переделать как api будет готово
    fun register(registrationRequest: RegistrationRequest) {
        _state.value = Loading

        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()

        viewModelScope.launch(handleError) {
            //TODO избавиться от delay
            delay(1000)

            if (registrationRequest.email.contains(emailRegex) && registrationRequest.password.isNotBlank()) {
                registerUseCase(registrationRequest)
                setTokenUseCase(registrationRequest.email + registrationRequest.password)
            } else
                _state.value = Error(HTTP400)
        }
    }
}