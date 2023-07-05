package com.example.plancton.di

import com.example.plancton.navigation.router.EntryRouter
import com.example.plancton.navigation.router.EntryRouterImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    companion object {
        private val cicerone: Cicerone<Router> = Cicerone.create()

        @Provides
        @AppScope
        fun provideRouter(): Router = cicerone.router

        @Provides
        @AppScope
        fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
    }

    @Binds
    @AppScope
    fun bindEntryRouter(impl: EntryRouterImpl): EntryRouter
}