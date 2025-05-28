package com.example.Shwas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.example.Shwas.ui.events.AppHostEvents
import com.example.Shwas.ui.sideEffects.OneShotEffect
import com.example.Shwas.ui.theme.Colors
import com.example.Shwas.ui.uiStates.AppHostUIState
import com.example.Shwas.ui.viewModels.AppHostViewModel
import androidx.compose.runtime.Composable as ComposableAnnotation

@ComposableAnnotation
fun AppHostScreen(
    viewModel: AppHostViewModel = hiltViewModel(),
    modifier: Modifier,
    navController: NavHostController
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    OneShotEffect {
        viewModel.handleEvent(AppHostEvents.SetStartDestination)
    }

    Box(
        modifier = Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    Colors.LightSkyBlue,
                    Colors.SkyBlue
                )
            )
        )
    ) {
        if (screenState.startDestination == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        screenState.startDestination?.let {
            NavHost(
                navController = navController,
                graph = navController.createGraph(startDestination = it) {
                    composable<ScreenType.LanguageScreen> {
                        LanguageScreen(modifier = modifier)
                    }
                    composable<ScreenType.HomeScreen> {
                        HomeScreen()
                    }
                    composable<ScreenType.MeditationScreen> { backEntry ->
                        val meditation = backEntry.toRoute<ScreenType.MeditationScreen>()
                        MeditationScreen(
                            id = meditation.meditationId,
                            cardColor = meditation.cardColor,
                            textColor = meditation.textColor,
                            accentColor = meditation.accentColor
                        )
                    }
                }
            )
        }
    }
}