package dev.donmanuel.pexelskmp.app.domain.usecases

import dev.donmanuel.pexelskmp.app.domain.models.Photo
import dev.donmanuel.pexelskmp.app.domain.services.ImageService

class DownloadImageUseCase(
    private val imageService: ImageService
) {
    suspend operator fun invoke(photo: Photo): Result<String> {
        return imageService.downloadImage(photo)
    }
} 