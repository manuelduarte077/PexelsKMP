package dev.donmanuel.pexelskmp.app.data.api

import dev.donmanuel.pexelskmp.app.domain.models.PexelsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException

class PexelsApiService(private val httpClient: HttpClient) {

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val API_KEY = "p6G6FNX7hs9Ncti8OJcDWsdkqBIqtdIBzHrVrboxqrdmlkDAP5iZjq9s"
    }

    suspend fun getCuratedPhotos(page: Int, perPage: Int): PexelsResponse {
        return try {
            val response = httpClient.get("$BASE_URL/curated") {
                header("Authorization", API_KEY)
                parameter("page", page)
                parameter("per_page", perPage)
            }

            response.body<PexelsResponse>()
        } catch (e: SerializationException) {
            try {
                val rawResponse = httpClient.get("$BASE_URL/curated") {
                    header("Authorization", API_KEY)
                    parameter("page", page)
                    parameter("per_page", perPage)
                }
                println("JSON raw: ${rawResponse.bodyAsText()}")
            } catch (debugException: Exception) {
                println("Error al obtener JSON raw: ${debugException.message}")
            }
            throw e
        }
    }

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): PexelsResponse {
        return try {
            val response = httpClient.get("$BASE_URL/search") {
                header("Authorization", API_KEY)
                parameter("query", query)
                parameter("page", page)
                parameter("per_page", perPage)
            }

            response.body<PexelsResponse>()
        } catch (e: SerializationException) {
            println("Error de serialización en searchPhotos: ${e.message}")
            try {
                val rawResponse = httpClient.get("$BASE_URL/search") {
                    header("Authorization", API_KEY)
                    parameter("query", query)
                    parameter("page", page)
                    parameter("per_page", perPage)
                }

                println("JSON raw: ${rawResponse.bodyAsText()}")
            } catch (debugException: Exception) {
                println("Error al obtener JSON raw: ${debugException.message}")
            }
            throw e
        }
    }
}