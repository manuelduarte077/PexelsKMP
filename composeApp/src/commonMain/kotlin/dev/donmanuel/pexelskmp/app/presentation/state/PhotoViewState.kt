package dev.donmanuel.pexelskmp.app.presentation.state

import dev.donmanuel.pexelskmp.app.domain.models.Photo

data class PhotoViewState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null,
    val isLoadingMore: Boolean = false,
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true,
    val searchQuery: String = ""
)