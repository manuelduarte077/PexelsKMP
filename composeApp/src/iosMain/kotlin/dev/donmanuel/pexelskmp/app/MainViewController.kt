package dev.donmanuel.pexelskmp.app

import androidx.compose.ui.window.ComposeUIViewController
import dev.donmanuel.pexelskmp.app.di.initKoin
import dev.donmanuel.pexelskmp.app.di.iosAppModule

fun MainViewController() = ComposeUIViewController(
    configure = { 
        initKoin {
            modules(iosAppModule)
        }
    }
) {
    App()
}