package com.example.plancton.feature.auth.login.di.component

import android.content.Context
import androidx.savedstate.SavedStateRegistryOwner
import com.example.plancton.feature.auth.login.di.annotation.LoginScope
import com.example.plancton.feature.auth.login.presentation.LoginViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@LoginScope
@Subcomponent
interface LoginComponent {

    fun viewModel(): LoginViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner,
        ): LoginComponent
    }
}