package dev.donmanuel.pexelskmp.app.domain.services

import dev.donmanuel.pexelskmp.app.domain.models.Photo

class DesktopImageService : ImageService {
    override suspend fun downloadImage(photo: Photo): Result<String> {
        // Desktop implementation would use file system APIs
        // For now, return a stub implementation
        return Result.failure(NotImplementedError("Image download not implemented for desktop yet"))
    }

    override suspend fun setAsWallpaper(photo: Photo): Result<Unit> {
        // Desktop implementation would use system wallpaper APIs
        // For now, return a stub implementation
        return Result.failure(NotImplementedError("Wallpaper setting not implemented for desktop yet"))
    }
} 