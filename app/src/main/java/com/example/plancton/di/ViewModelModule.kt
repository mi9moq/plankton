package com.example.plancton.di

import androidx.lifecycle.ViewModel
import com.example.plancton.presentation.entry.EntryViewModel
import com.example.plancton.presentation.event.EventViewModel
import com.example.plancton.presentation.login.LoginViewModel
import com.example.plancton.presentation.main.MainViewModel
import com.example.plancton.presentation.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    fun bindEventViewModel(impl: EventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EntryViewModel::class)
    fun bindEntryViewModel(viewModel: EntryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(impl: MainViewModel): ViewModel
}