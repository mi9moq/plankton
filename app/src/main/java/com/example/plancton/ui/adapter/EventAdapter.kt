package com.example.plancton.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.plancton.domain.entity.UserEvent

class EventAdapter(
    private val onClick: (UserEvent) -> Unit
) : ListAdapter<UserEvent, EventViewHolder>(EventItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}