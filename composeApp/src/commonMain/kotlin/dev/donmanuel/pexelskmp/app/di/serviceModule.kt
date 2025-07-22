package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.domain.services.ImageService
import org.koin.dsl.module

val serviceModule = module {
    single<ImageService> {
        throw NotImplementedError("ImageService not implemented for this platform") 
    }
} 