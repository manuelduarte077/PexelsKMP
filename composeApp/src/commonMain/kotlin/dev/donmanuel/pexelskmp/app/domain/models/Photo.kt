package dev.donmanuel.pexelskmp.app.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    @SerialName("photographer_url") val photographerUrl: String,
    @SerialName("photographer_id") val photographerId: Int,
    @SerialName("avg_color") val avgColor: String,
    val src: PhotoSrc,
    val liked: Boolean,
    val alt: String
)