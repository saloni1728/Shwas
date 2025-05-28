package com.example.Shwas.ui.events

import androidx.lifecycle.ViewModel

sealed class Events {
    abstract class UIEvent : Events()
}

abstract class EventHandlerViewModel<in T: Events.UIEvent>: ViewModel() {
    abstract fun handleEvent(event: T)
}