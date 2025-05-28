package com.example.Shwas.ui.sideEffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun OneShotEffect(producer: suspend () -> Unit) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!isInitialized) {
            producer.invoke()
            isInitialized = true
        }
    }
}