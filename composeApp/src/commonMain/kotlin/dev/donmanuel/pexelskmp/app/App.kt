package dev.donmanuel.pexelskmp.app

import androidx.compose.runtime.Composable
import dev.donmanuel.pexelskmp.app.presentation.screens.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PexelsTheme {
        SplashScreen()
    }
}