package com.example.plancton.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.core.token.domain.usecase.GetTokenUseCase
import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import com.example.plancton.core.user.domain.usecase.ChangeUserUseCase
import com.example.plancton.core.user.domain.usecase.GetUserUseCase
import com.example.plancton.presentation.user.UserState.Content
import com.example.plancton.presentation.user.UserState.Initial
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val changeUserUseCase: ChangeUserUseCase,
) : ViewModel() {

    private val _state: MutableLiveData<UserState> = MutableLiveData(Initial)
    val state: LiveData<UserState> = _state

    init {
        getUser()
    }

    private fun getUser() {
        //TODO добавить обработку ошибок
        viewModelScope.launch {
            val token = getTokenUseCase()
            val user = getUserUseCase(token!!)
            _state.value = Content(user)
        }
    }

    fun change(changeUserRequest: ChangeUserRequest) {
        viewModelScope.launch {
            val token = getTokenUseCase() //Специфика работы бэкэнда
            changeUserUseCase(token!!, changeUserRequest)
        }
    }
}