package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.data.repositories.PhotoRepositoryImpl
import dev.donmanuel.pexelskmp.app.domain.repositories.PhotoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotoRepository> { PhotoRepositoryImpl(get()) }
}