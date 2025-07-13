package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.domain.usecases.GetCuratedPhotosUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.SearchPhotosUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCuratedPhotosUseCase(get()) }
    single { SearchPhotosUseCase(get()) }
}