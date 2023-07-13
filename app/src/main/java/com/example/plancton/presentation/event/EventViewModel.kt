package com.example.plancton.presentation.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.ReplayType
import com.examlpe.plancton.core.event.domain.usecase.CreateSingleEventUseCase
import com.example.plancton.navigation.router.EventRouter
import com.example.plancton.presentation.event.EventState.Initial
import com.example.plancton.presentation.event.EventState.Loading
import com.example.plancton.ui.utils.handleEventError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val createSingleEventUseCase: CreateSingleEventUseCase,
    private val router: EventRouter,
) : ViewModel() {

    private val _state: MutableLiveData<EventState> = MutableLiveData(Initial)
    val state: LiveData<EventState> = _state

    private val _date: MutableLiveData<LocalDate?> = MutableLiveData(null)
    val date: LiveData<LocalDate?> = _date

    private val _time: MutableLiveData<LocalTime?> = MutableLiveData(null)
    val time: LiveData<LocalTime?> = _time

    private val _replayTypes: MutableLiveData<List<ReplayType>> =
        MutableLiveData(ReplayType.values().toList())
    val replayTypes: LiveData<List<ReplayType>> = _replayTypes

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _state.value = EventState.Error(handleEventError(throwable))
    }

    fun setDate(timeMillis: Long) {
        _date.value = Instant.ofEpochMilli(timeMillis).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun setTime(hour: Int, minute: Int) {
        _time.value = LocalTime.of(hour, minute)
    }

    fun create(date: LocalDate, time: LocalTime, inputDescription: String?) {
        val description = inputDescription ?: ""
        val event = EventRequest(date, time, description)

        viewModelScope.launch(handler) {
            _state.value = Loading
            createSingleEventUseCase(event)
            router.back()
        }
    }

    fun tryAgain() {
        _state.value = Initial
        _time.value = null
        _date.value = null
    }

    fun reLogin() {
        //TODO delete token
        router.openEntry()
    }
}