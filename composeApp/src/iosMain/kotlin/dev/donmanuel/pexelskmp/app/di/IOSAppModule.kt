package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.domain.services.IOSImageService
import dev.donmanuel.pexelskmp.app.domain.services.ImageService
import org.koin.dsl.module

val iosAppModule = module {
    // Provide iOS-specific ImageService implementation
    single<ImageService> { 
        IOSImageService() 
    }
} 