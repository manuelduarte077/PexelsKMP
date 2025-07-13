package dev.donmanuel.pexelskmp.app.domain.usecases

import dev.donmanuel.pexelskmp.app.domain.models.PexelsResponse
import dev.donmanuel.pexelskmp.app.domain.repositories.PhotoRepository

class GetCuratedPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        perPage: Int = 20
    ): Result<PexelsResponse> {
        return repository.getCuratedPhotos(page, perPage)
    }
}