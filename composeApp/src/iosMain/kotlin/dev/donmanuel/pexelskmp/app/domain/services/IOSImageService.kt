package dev.donmanuel.pexelskmp.app.domain.services

import dev.donmanuel.pexelskmp.app.domain.models.Photo

class IOSImageService : ImageService {
    override suspend fun downloadImage(photo: Photo): Result<String> {
        // iOS implementation would use Photos framework
        // For now, return a stub implementation
        return Result.failure(NotImplementedError("Image download not implemented for iOS yet"))
    }

    override suspend fun setAsWallpaper(photo: Photo): Result<Unit> {
        // iOS implementation would use wallpaper setting APIs
        // For now, return a stub implementation
        return Result.failure(NotImplementedError("Wallpaper setting not implemented for iOS yet"))
    }
} 