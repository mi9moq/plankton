package com.example.plancton.di

import android.content.Context
import com.examlpe.plancton.core.event.EventCoreModule
import com.example.plancton.PlanctonApp
import com.example.plancton.core.auth.AuthCoreModule
import com.example.plancton.core.dagger.AppScope
import com.example.plancton.core.token.TokenCoreModule
import com.example.plancton.ui.activity.MainActivity
import com.example.plancton.ui.fragment.EntryFragment
import com.example.plancton.ui.fragment.EventFragment
import com.example.plancton.ui.fragment.LoginFragment
import com.example.plancton.ui.fragment.MainFragment
import com.example.plancton.ui.fragment.RegistrationFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class,
        NavigationModule::class,
        AuthCoreModule::class,
        EventCoreModule::class,
        TokenCoreModule::class,
    ]
)
@AppScope
interface AppComponent {

    fun inject(app: PlanctonApp)

    fun inject(activity: MainActivity)

    fun inject(fragment: EventFragment)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: RegistrationFragment)

    fun inject(fragment: EntryFragment)

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}