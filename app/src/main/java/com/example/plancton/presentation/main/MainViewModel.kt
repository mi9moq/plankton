package com.example.plancton.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examlpe.plancton.core.event.domain.usecase.DeleteEventUseCase
import com.examlpe.plancton.core.event.domain.usecase.GetEventsUseCase
import com.example.plancton.navigation.router.MainRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
    private val router: MainRouter,
) : ViewModel() {

    companion object {
        private const val YEAR_MILLIS = 24 * 60 * 60 * 1000 * 365L
    }

    private val _state: MutableLiveData<MainState> = MutableLiveData(MainState.Initial)
    val state: LiveData<MainState> = _state

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
        viewModelScope.launch {
            _state.value = MainState.Loading
            delay(2500L)
            _state.value = MainState.Content(
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
        }
    }

    fun openAdd() {
        router.openAddEvent()
    }

    private fun getStartDate(timeInMillis: Long): LocalDate =
        Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDate()

    private fun getEndDate(timeInMillis: Long): LocalDate =
        Instant.ofEpochMilli(timeInMillis + YEAR_MILLIS).atZone(ZoneId.systemDefault())
            .toLocalDate()
}