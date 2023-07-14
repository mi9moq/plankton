package com.example.plancton.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plancton.R
import com.example.plancton.databinding.EventItemBinding
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import com.example.plancton.ui.utils.formatDate
import com.example.plancton.ui.utils.formatTime

class EventViewHolder(
    parent: ViewGroup,
    private val onClick: (UserEvent) -> Unit,
    private val onLongClickListener: (UserEvent) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
) {

    private val binding = EventItemBinding.bind(itemView)

    fun bind(event: UserEvent) {
        with(binding) {
            date.text = formatDate(event.date)
            time.text = formatTime(event.time)
            description.text = event.description
        }
        itemView.setOnClickListener {
            onClick(event)
        }
        itemView.setOnLongClickListener {
            onLongClickListener(event)
            true
        }
    }
}