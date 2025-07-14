package dev.donmanuel.pexelskmp.app

import android.app.Application
import dev.donmanuel.pexelskmp.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class PexelsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PexelsApplication)
            modules(appModule)
        }
    }
}