package dev.donmanuel.pexelskmp.app.domain.usecases

import dev.donmanuel.pexelskmp.app.domain.models.Photo
import dev.donmanuel.pexelskmp.app.domain.services.ImageService

class SetWallpaperUseCase(
    private val imageService: ImageService
) {
    suspend operator fun invoke(photo: Photo): Result<Unit> {
        return imageService.setAsWallpaper(photo)
    }
} 