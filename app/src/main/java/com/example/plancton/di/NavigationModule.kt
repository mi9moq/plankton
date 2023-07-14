package com.example.plancton.di

import com.example.plancton.core.dagger.AppScope
import com.example.plancton.navigation.router.ActivityRouter
import com.example.plancton.navigation.router.ActivityRouterImpl
import com.example.plancton.navigation.router.EntryRouter
import com.example.plancton.navigation.router.EntryRouterImpl
import com.example.plancton.navigation.router.EventRouter
import com.example.plancton.navigation.router.EventRouterImpl
import com.example.plancton.navigation.router.LoginRouter
import com.example.plancton.navigation.router.LoginRouterImpl
import com.example.plancton.navigation.router.MainRouter
import com.example.plancton.navigation.router.MainRouterImpl
import com.example.plancton.navigation.router.RegisterRouter
import com.example.plancton.navigation.router.RegisterRouterImpl
import com.example.plancton.navigation.router.UserRouter
import com.example.plancton.navigation.router.UserRouterImpl
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

    @Binds
    @AppScope
    fun bindMainRouter(impl: MainRouterImpl): MainRouter

    @Binds
    @AppScope
    fun bindEventRouter(impl: EventRouterImpl): EventRouter

    @Binds
    @AppScope
    fun bindLoginRouter(impl: LoginRouterImpl): LoginRouter

    @Binds
    @AppScope
    fun bindRegisterLogin(impl: RegisterRouterImpl): RegisterRouter

    @Binds
    @AppScope
    fun bindActivityRouter(impl: ActivityRouterImpl): ActivityRouter

    @Binds
    @AppScope
    fun bindUserRouter(impl: UserRouterImpl): UserRouter
}