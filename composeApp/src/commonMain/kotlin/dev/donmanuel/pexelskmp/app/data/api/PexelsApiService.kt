package dev.donmanuel.pexelskmp.app.data.api

import dev.donmanuel.pexelskmp.app.domain.models.PexelsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

class PexelsApiService(private val httpClient: HttpClient) {

    companion object {
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val API_KEY = "p6G6FNX7hs9Ncti8OJcDWsdkqBIqtdIBzHrVrboxqrdmlkDAP5iZjq9s"
    }

    suspend fun getCuratedPhotos(page: Int, perPage: Int): PexelsResponse {
        return httpClient.get("$BASE_URL/curated") {
            header("Authorization", API_KEY)
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    }

    suspend fun searchPhotos(query: String, page: Int, perPage: Int): PexelsResponse {
        return httpClient.get("$BASE_URL/search") {
            header("Authorization", API_KEY)
            parameter("query", query)
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    }
}