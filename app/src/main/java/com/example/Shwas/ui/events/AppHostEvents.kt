package com.example.Shwas.ui.events

sealed class AppHostEvents: Events.UIEvent() {
    object SetStartDestination: AppHostEvents()
}