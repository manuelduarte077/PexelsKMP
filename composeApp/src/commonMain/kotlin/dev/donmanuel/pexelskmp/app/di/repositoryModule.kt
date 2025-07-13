package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.data.repositories.PhotoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { PhotoRepositoryImpl(get()) }
}