package com.example.Shwas.navigation


import androidx.navigation.NavController
import com.example.Shwas.ui.screens.ScreenType
import javax.inject.Inject

class Navigator @Inject constructor(): INavigator {
    private var navController: NavController? = null

    override fun setNavController(navController: NavController?) {
        this.navController = navController
    }

    override fun navigateUp() {
        navController?.navigateUp()
    }

    override fun navigateTo(destination: ScreenType, params: Map<String, Any>) {
        navController?.navigate(destination)
        // Implementation for navigating to a specific destination with optional parameters
    }

    override fun navigateBack(count: Int) {
        // Implementation for navigating back in the navigation stack by a specified count
    }
}