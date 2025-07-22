package dev.donmanuel.pexelskmp.app.presentation.intent

import dev.donmanuel.pexelskmp.app.domain.models.Photo

sealed class PhotoIntent {
    object LoadCuratedPhotos : PhotoIntent()
    data class SearchPhotos(val query: String) : PhotoIntent()
    object LoadMorePhotos : PhotoIntent()
    object RetryLoading : PhotoIntent()
    data class DownloadPhoto(val photo: Photo) : PhotoIntent()
    data class SetWallpaper(val photo: Photo) : PhotoIntent()
}