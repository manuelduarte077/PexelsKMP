package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.presentation.viewmodel.PhotoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::PhotoViewModel)
}