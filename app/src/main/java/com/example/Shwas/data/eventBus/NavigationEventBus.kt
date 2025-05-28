package com.example.Shwas.data.eventBus

import androidx.navigation.NavController
import com.example.Shwas.navigation.INavigator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class NavigationEventBus @Inject constructor(private val navigator: INavigator) {
    private val _navigationEvent = MutableSharedFlow<NavigationEvents>(extraBufferCapacity = 1)
    private val navigationEvent = _navigationEvent.asSharedFlow()

    suspend fun navigate(event: NavigationEvents) {
        _navigationEvent.emit(event)
    }

    suspend fun subscribe(navController: NavController) {
        navigator.setNavController(navController)
        navigationEvent.collectLatest { event ->
            when (event) {
                is NavigationEvents.NavigateUp -> {
                    navigator.navigateUp()
                }
                is NavigationEvents.NavigateTo -> {
                    navigator.navigateTo(event.route, event.params)
                }
                is NavigationEvents.NavigateBack -> {
                    navigator.navigateBack(event.count)
                }
            }
        }
    }
}