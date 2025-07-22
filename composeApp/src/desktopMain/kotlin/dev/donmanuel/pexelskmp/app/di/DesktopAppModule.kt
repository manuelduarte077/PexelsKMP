package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.domain.services.DesktopImageService
import dev.donmanuel.pexelskmp.app.domain.services.ImageService
import org.koin.dsl.module

val desktopAppModule = module {
    // Provide desktop-specific ImageService implementation
    single<ImageService> { 
        DesktopImageService() 
    }
} 