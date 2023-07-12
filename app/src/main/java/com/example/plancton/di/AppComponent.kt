package com.example.plancton.di

import android.content.Context
import com.example.plancton.PlanctonApp
import com.example.plancton.core.auth.AuthCoreModule
import com.example.plancton.core.dagger.AppScope
import com.example.plancton.feature.auth.login.di.annotation.LoginScope
import com.example.plancton.feature.auth.login.di.component.LoginComponent
import com.example.plancton.ui.activity.MainActivity
import com.example.plancton.ui.fragment.EntryFragment
import com.example.plancton.ui.fragment.EventFragment
import com.example.plancton.ui.fragment.MainFragment
import com.example.plancton.ui.fragment.RegistrationFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RepositoryModule::class,
        ViewModelModule::class,
        NavigationModule::class,
        DataSourceModule::class,
        AuthCoreModule::class,
    ]
)
@AppScope
interface AppComponent {

    fun inject(app: PlanctonApp)

    fun inject(activity: MainActivity)

    fun inject(fragment: EventFragment)

    // fun inject(fragment: LoginFragment)

    fun inject(fragment: RegistrationFragment)

    fun inject(fragment: EntryFragment)

    fun inject(fragment: MainFragment)

    @LoginScope
    val loginComponentFactory: LoginComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}