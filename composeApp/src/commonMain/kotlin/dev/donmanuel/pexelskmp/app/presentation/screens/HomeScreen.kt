package dev.donmanuel.pexelskmp.app.presentation.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class HomeScreen() : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        HomeScreenContent(
            onBackClick = {
                navigator?.push(ExitScreen())
            }
        )
    }
}