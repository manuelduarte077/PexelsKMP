package dev.donmanuel.pexelskmp.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.donmanuel.pexelskmp.app.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pexels KMP",
    ) {
        App()
    }
}