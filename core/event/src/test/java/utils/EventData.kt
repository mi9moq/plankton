package utils

import java.time.LocalDate
import java.time.LocalTime
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import com.examlpe.plancton.core.event.data.network.model.UserEventDto

object EventData {

    private val date = LocalDate.now()
    private val time = LocalTime.now()

    val request = EventRequest(date, time, "des")

    val event = UserEvent(date, time, "qwe", "12", "11")
    val eventDto = UserEventDto(date, time, "qwe", "12", "11")

    val eventList = listOf(event)

    val listDto = listOf(eventDto)

    val startDate = date

    val endDate = date
}