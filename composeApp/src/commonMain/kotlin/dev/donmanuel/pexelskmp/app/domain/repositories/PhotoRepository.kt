package dev.donmanuel.pexelskmp.app.domain.repositories

import dev.donmanuel.pexelskmp.app.domain.models.PexelsResponse

interface PhotoRepository {
    suspend fun getCuratedPhotos(page: Int, perPage: Int): Result<PexelsResponse>
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): Result<PexelsResponse>
}