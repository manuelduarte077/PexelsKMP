package dev.donmanuel.pexelskmp.app

import dev.donmanuel.pexelskmp.app.di.appModule
import org.koin.core.context.startKoin

fun initKoinCommon() {
    startKoin {
        modules(appModule)
    }
}