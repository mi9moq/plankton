package com.example.plancton.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.plancton.domain.entity.UserEvent

class EventItemDiffCallback : DiffUtil.ItemCallback<UserEvent>() {

    override fun areItemsTheSame(oldItem: UserEvent, newItem: UserEvent): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UserEvent, newItem: UserEvent): Boolean =
        oldItem == newItem
}