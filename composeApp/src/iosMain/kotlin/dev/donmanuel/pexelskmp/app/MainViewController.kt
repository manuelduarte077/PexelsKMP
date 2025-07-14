package dev.donmanuel.pexelskmp.app

import androidx.compose.ui.window.ComposeUIViewController
import dev.donmanuel.pexelskmp.app.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}