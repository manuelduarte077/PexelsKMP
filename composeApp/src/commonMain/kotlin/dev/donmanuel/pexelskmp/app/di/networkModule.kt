package dev.donmanuel.pexelskmp.app.di

import dev.donmanuel.pexelskmp.app.data.api.PexelsApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                    useArrayPolymorphism = true
                    encodeDefaults = true
                    explicitNulls = false
                    prettyPrint = false
                    classDiscriminator = "type"
                })
            }
        }
    }

    single { PexelsApiService(get()) }
}