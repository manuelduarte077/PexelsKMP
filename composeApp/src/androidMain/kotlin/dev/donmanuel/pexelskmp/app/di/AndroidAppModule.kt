package dev.donmanuel.pexelskmp.app.di

import android.content.Context
import dev.donmanuel.pexelskmp.app.domain.services.AndroidImageService
import dev.donmanuel.pexelskmp.app.domain.services.ImageService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidAppModule = module {
    // Provide Android-specific ImageService implementation
    single<ImageService> { 
        AndroidImageService(androidContext()) 
    }
} 