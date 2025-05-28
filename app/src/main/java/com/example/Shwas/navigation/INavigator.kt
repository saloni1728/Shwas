package com.example.Shwas.navigation

import androidx.navigation.NavController
import com.example.Shwas.ui.screens.ScreenType

interface INavigator {
    fun navigateUp()
    fun navigateTo(destination: ScreenType, params: Map<String, Any>)
    fun navigateBack(count: Int = 1)
    fun setNavController(navController: NavController?)
}