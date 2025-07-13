package dev.donmanuel.pexelskmp.app.presentation.intent

sealed class PhotoIntent {
    object LoadCuratedPhotos : PhotoIntent()
    data class SearchPhotos(val query: String) : PhotoIntent()
    object LoadMorePhotos : PhotoIntent()
    object RetryLoading : PhotoIntent()
}