package dev.donmanuel.pexelskmp.app

import androidx.compose.runtime.Composable
import dev.donmanuel.pexelskmp.app.presentation.screens.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PexelsTheme {
        HomeScreen()
    }
}