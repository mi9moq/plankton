package com.example.plancton.di

import androidx.lifecycle.ViewModel
import com.example.plancton.presentation.viewmodel.EntryViewModel
import com.example.plancton.presentation.viewmodel.LoginViewModel
import com.example.plancton.presentation.viewmodel.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

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
}