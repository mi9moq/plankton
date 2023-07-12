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

    fun setDate(timeMillis: Long) {
        _date.value = Instant.ofEpochMilli(timeMillis).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun setTime(hour: Int, minute: Int) {
        _time.value = LocalTime.of(hour, minute)
    }

    fun create(date: LocalDate, time: LocalTime, inputDescription: String?) {
        val description = inputDescription ?: ""
        val event = EventRequest(date, time, description)

        //TODO добавить handler
        viewModelScope.launch {
            _state.value = Loading
            createSingleEventUseCase(event)
            router.back()
        }
    }
}