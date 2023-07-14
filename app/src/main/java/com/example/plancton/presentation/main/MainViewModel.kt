package com.example.plancton.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examlpe.plancton.core.event.domain.usecase.DeleteEventUseCase
import com.examlpe.plancton.core.event.domain.usecase.GetEventsUseCase
import com.example.plancton.core.token.domain.usecase.DeleteTokenUseCase
import com.example.plancton.navigation.router.MainRouter
import com.example.plancton.presentation.main.MainState.Content
import com.example.plancton.presentation.main.MainState.Error
import com.example.plancton.presentation.main.MainState.Initial
import com.example.plancton.presentation.main.MainState.Loading
import com.example.plancton.ui.utils.handleEventError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val router: MainRouter,
) : ViewModel() {

    companion object {
        private const val YEAR_MILLIS = 24 * 60 * 60 * 1000 * 365L
    }

    private val _state: MutableLiveData<MainState> = MutableLiveData(Initial)
    val state: LiveData<MainState> = _state

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _state.value = Error(handleEventError(throwable))
    }

    init {
        loadEvents()
    }

    fun loadEvents() {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        viewModelScope.launch(handler) {
            _state.value = Loading
            _state.value = Content(
                getEventsUseCase(
                    getStartDate(calendar.timeInMillis),
                    getEndDate(calendar.timeInMillis)
                )
            )
        }
    }

    fun deleteEvent(id: String) {
        viewModelScope.launch {
            deleteEventUseCase(id)
            loadEvents()
        }
    }

    fun openAdd() {
        router.openAddEvent()
    }

    fun reLogin() {
        deleteTokenUseCase()
        router.openLogin()
    }

    fun editUser() {
        router.openEditUser()
    }

    private fun getStartDate(timeInMillis: Long): LocalDate =
        Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDate()

    private fun getEndDate(timeInMillis: Long): LocalDate =
        Instant.ofEpochMilli(timeInMillis + YEAR_MILLIS).atZone(ZoneId.systemDefault())
            .toLocalDate()
}