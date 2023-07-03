package com.example.plancton.di

import android.app.Application
import com.example.plancton.PlanctonApp
import dagger.BindsInstance
import dagger.Component

@Component
@AppScope
interface AppComponent {

    fun inject(app: PlanctonApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}