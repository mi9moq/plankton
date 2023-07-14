package com.example.plancton.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.examlpe.plancton.core.event.domain.entity.UserEvent

class EventAdapter(
    private val onClick: (UserEvent) -> Unit,
    private val onLongClickListener: (UserEvent) -> Unit,
) : ListAdapter<UserEvent, EventViewHolder>(EventItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(parent, onClick, onLongClickListener)

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}