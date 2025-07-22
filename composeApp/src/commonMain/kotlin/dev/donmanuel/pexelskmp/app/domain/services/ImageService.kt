package dev.donmanuel.pexelskmp.app.domain.services

import dev.donmanuel.pexelskmp.app.domain.models.Photo

interface ImageService {
    suspend fun downloadImage(photo: Photo): Result<String>
    suspend fun setAsWallpaper(photo: Photo): Result<Unit>
} 