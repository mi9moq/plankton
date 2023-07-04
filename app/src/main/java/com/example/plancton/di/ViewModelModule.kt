package com.example.plancton.di

import androidx.lifecycle.ViewModel
import com.example.plancton.presentation.event.EventViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    fun bindEventViewModel(impl: EventViewModel): ViewModel
}