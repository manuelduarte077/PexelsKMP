package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.presentation.viewmodel.PhotoViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { PhotoViewModel(get(), get()) }
}