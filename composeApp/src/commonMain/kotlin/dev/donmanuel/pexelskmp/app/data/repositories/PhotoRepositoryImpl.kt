package dev.donmanuel.pexelskmp.app.data.repositories

import dev.donmanuel.pexelskmp.app.data.api.PexelsApiService
import dev.donmanuel.pexelskmp.app.domain.models.PexelsResponse
import dev.donmanuel.pexelskmp.app.domain.repositories.PhotoRepository
import kotlinx.serialization.SerializationException

class PhotoRepositoryImpl(
    private val apiService: PexelsApiService
) : PhotoRepository {

    override suspend fun getCuratedPhotos(page: Int, perPage: Int): Result<PexelsResponse> {
        return try {
            val response = apiService.getCuratedPhotos(page, perPage)
            Result.success(response)
        } catch (e: SerializationException) {
            Result.failure(Exception("Error al procesar los datos de la API: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Result<PexelsResponse> {
        return try {
            val response = apiService.searchPhotos(query, page, perPage)
            Result.success(response)
        } catch (e: SerializationException) {
            Result.failure(Exception("Error al procesar los datos de la API: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}