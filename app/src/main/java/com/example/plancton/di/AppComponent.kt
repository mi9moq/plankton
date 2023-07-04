package com.example.plancton.di

import android.app.Application
import com.example.plancton.PlanctonApp
import com.example.plancton.ui.activity.MainActivity
import com.example.plancton.ui.fragment.LoginFragment
import com.example.plancton.ui.fragment.RegistrationFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
@AppScope
interface AppComponent {

    fun inject(app: PlanctonApp)

    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: RegistrationFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}