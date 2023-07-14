package com.example.plancton.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.core.token.domain.usecase.DeleteTokenUseCase
import com.example.plancton.core.token.domain.usecase.GetTokenUseCase
import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import com.example.plancton.core.user.domain.usecase.ChangeUserUseCase
import com.example.plancton.core.user.domain.usecase.GetUserUseCase
import com.example.plancton.navigation.router.UserRouter
import com.example.plancton.presentation.user.UserState.Content
import com.example.plancton.presentation.user.UserState.Error
import com.example.plancton.presentation.user.UserState.Initial
import com.example.plancton.presentation.user.UserState.Loading
import com.example.plancton.ui.utils.handleUserError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val changeUserUseCase: ChangeUserUseCase,
    private val router: UserRouter,
    private val deleteTokenUseCase: DeleteTokenUseCase,
) : ViewModel() {

    private val _state: MutableLiveData<UserState> = MutableLiveData(Initial)
    val state: LiveData<UserState> = _state

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _state.value = Error(handleUserError(throwable))
    }

    init {
        getUser()
    }

    private fun getUser() {
        _state.value = Loading

        viewModelScope.launch(handler) {
            val token = getTokenUseCase()
            val user = getUserUseCase(token!!)
            _state.value = Content(user)
        }
    }

    fun change(fullName: String) {
        _state.value = Loading

        viewModelScope.launch(handler) {
            val token = getTokenUseCase() //Специфика работы бэкэнда
            changeUserUseCase(token!!, ChangeUserRequest(fullName))
            router.backToMain()
        }
    }

    fun reLogin() {
        deleteTokenUseCase()
        router.openLogin()
    }

    fun tryAgain() {
        _state.value = Loading
        _state.value = Initial
    }
}