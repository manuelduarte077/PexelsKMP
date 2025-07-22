package dev.donmanuel.pexelskmp.app

import android.app.Application
import dev.donmanuel.pexelskmp.app.di.androidAppModule
import dev.donmanuel.pexelskmp.app.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class PexelsApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@PexelsApplication)
            modules(androidAppModule)
        }
    }
}