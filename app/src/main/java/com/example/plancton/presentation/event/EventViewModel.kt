package com.example.plancton.presentation.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plancton.domain.entity.ReplayType
import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.domain.usecase.CreateEventUseCase
import com.example.plancton.navigation.router.EventRouter
import com.example.plancton.presentation.event.EventState.Initial
import com.example.plancton.presentation.event.EventState.Loading
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val createEventUseCase: CreateEventUseCase,
    private val router: EventRouter,
) : ViewModel() {

    private val _state: MutableLiveData<EventState> = MutableLiveData(Initial)
    val state: LiveData<EventState> = _state

    private val _date: MutableLiveData<Date?> = MutableLiveData(null)
    val date: LiveData<Date?> = _date

    private val _time: MutableLiveData<Time?> = MutableLiveData(null)
    val time: LiveData<Time?> = _time

    private val _replayTypes: MutableLiveData<List<ReplayType>> = MutableLiveData(ReplayType.values().toList())
    val replayTypes: LiveData<List<ReplayType>> = _replayTypes

    fun setDate(timeMillis: Long) {
        _date.value = Date(timeMillis)
    }

    fun setTime(hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            timeZone = TimeZone.getDefault()
        }

        _time.value = Time(calendar.timeInMillis)
    }

    fun create(date: Date, time: Time, inputDescription: String?, replayType: String) {
        val description = inputDescription ?: ""
        val replay = ReplayType.valueOf(replayType)
        val userEvent = UserEvent(date, time, description, replay = replay)

        //TODO добавить handler
        viewModelScope.launch {
            _state.value = Loading
            delay(2500)
            createEventUseCase(userEvent)
            router.back()
        }
    }
}