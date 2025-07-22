package dev.donmanuel.pexelskmp.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dev.donmanuel.pexelskmp.app.navigation.NavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PexelsTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}