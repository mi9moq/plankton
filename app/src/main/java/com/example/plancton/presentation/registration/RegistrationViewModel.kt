package com.example.plancton.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import com.example.plancton.core.auth.domain.usecase.RegisterUseCase
import com.example.plancton.core.token.domain.usecase.SetTokenUseCase
import com.example.plancton.navigation.router.RegisterRouter
import com.example.plancton.presentation.registration.RegistrationState.Initial
import com.example.plancton.presentation.registration.RegistrationState.Loading
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val router: RegisterRouter,
) : ViewModel() {

    private val _state = MutableLiveData<RegistrationState>(Initial)
    val state: LiveData<RegistrationState> = _state

    private val handleError = CoroutineExceptionHandler { _, exception ->

    }

    fun register(registrationRequest: RegistrationRequest) {
        _state.value = Loading

        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()

        viewModelScope.launch(handleError) {

            if (registrationRequest.email.contains(emailRegex) && registrationRequest.password.isNotBlank()) {
                val token = registerUseCase(registrationRequest)
                setTokenUseCase(token)
                router.openMain()
            }
        }
    }
}