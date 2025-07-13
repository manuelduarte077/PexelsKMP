package dev.donmanuel.pexelskmp.app.domain.usecases

import dev.donmanuel.pexelskmp.app.domain.models.PexelsResponse
import dev.donmanuel.pexelskmp.app.domain.repositories.PhotoRepository

class SearchPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        perPage: Int = 20
    ): Result<PexelsResponse> {
        return repository.searchPhotos(
            query,
            page,
            perPage
        )
    }
}