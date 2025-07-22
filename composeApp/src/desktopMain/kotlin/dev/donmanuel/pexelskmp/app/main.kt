package dev.donmanuel.pexelskmp.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.donmanuel.pexelskmp.app.di.initKoin
import dev.donmanuel.pexelskmp.app.di.desktopAppModule

fun main() = application {
    initKoin {
        modules(desktopAppModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pexels KMP",
    ) {
        App()
    }
}