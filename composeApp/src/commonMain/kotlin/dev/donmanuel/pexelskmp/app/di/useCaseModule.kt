package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.domain.services.ImageService
import dev.donmanuel.pexelskmp.app.domain.repositories.PhotoRepository
import dev.donmanuel.pexelskmp.app.domain.usecases.DownloadImageUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.GetCuratedPhotosUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.SearchPhotosUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.SetWallpaperUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCuratedPhotosUseCase(get<PhotoRepository>()) }
    single { SearchPhotosUseCase(get<PhotoRepository>()) }
    single { DownloadImageUseCase(get<ImageService>()) }
    single { SetWallpaperUseCase(get<ImageService>()) }
}