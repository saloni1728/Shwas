package com.example.Shwas.data.di

import com.example.Shwas.data.eventBus.NavigationEventBus
import com.example.Shwas.navigation.INavigator
import com.example.Shwas.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class NavigationBindModule {
    @Binds
    @Singleton
    abstract fun bindNavigation(navigator: Navigator): INavigator
}

@InstallIn(SingletonComponent::class)
@Module
class NavigationManagerModule {
    @Provides
    @Singleton
    fun provideNavigationManager(navigator: INavigator): NavigationEventBus = NavigationEventBus(navigator)
}