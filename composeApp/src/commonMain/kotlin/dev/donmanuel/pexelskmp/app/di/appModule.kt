package dev.donmanuel.pexelskmp.app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }