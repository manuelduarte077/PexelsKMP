package dev.donmanuel.pexelskmp.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.donmanuel.pexelskmp.app.domain.models.Photo
import dev.donmanuel.pexelskmp.app.domain.usecases.DownloadImageUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.GetCuratedPhotosUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.SearchPhotosUseCase
import dev.donmanuel.pexelskmp.app.domain.usecases.SetWallpaperUseCase
import dev.donmanuel.pexelskmp.app.presentation.PhotoEffect
import dev.donmanuel.pexelskmp.app.presentation.intent.PhotoIntent
import dev.donmanuel.pexelskmp.app.presentation.state.PhotoViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoViewModel(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val setWallpaperUseCase: SetWallpaperUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow(PhotoViewState())
    val viewState = _viewState.asStateFlow()

    private val _effects = MutableSharedFlow<PhotoEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: PhotoIntent) {
        when (intent) {
            is PhotoIntent.LoadCuratedPhotos -> loadCuratedPhotos()
            is PhotoIntent.SearchPhotos -> searchPhotos(intent.query)
            is PhotoIntent.LoadMorePhotos -> loadMorePhotos()
            is PhotoIntent.RetryLoading -> retryLoading()
            is PhotoIntent.DownloadPhoto -> downloadPhoto(intent.photo)
            is PhotoIntent.SetWallpaper -> setWallpaper(intent.photo)
        }
    }

    private fun loadCuratedPhotos() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true, error = null, searchQuery = "") }

            getCuratedPhotosUseCase(page = 1).fold(
                onSuccess = { response ->
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            photos = response.photos,
                            currentPage = response.page,
                            hasMorePages = response.nextPage != null
                        )
                    }
                },
                onFailure = { exception ->
                    _viewState.update {
                        it.copy(isLoading = false, error = exception.message)
                    }
                    _effects.emit(PhotoEffect.ShowError(exception.message ?: "Unknown error"))
                }
            )
        }
    }

    private fun searchPhotos(query: String) {
        if (query.isBlank()) {
            loadCuratedPhotos()
            return
        }

        viewModelScope.launch {
            _viewState.update {
                it.copy(isLoading = true, error = null, searchQuery = query)
            }

            searchPhotosUseCase(query, page = 1).fold(
                onSuccess = { response ->
                    _viewState.update {
                        it.copy(
                            isLoading = false,
                            photos = response.photos,
                            currentPage = response.page,
                            hasMorePages = response.nextPage != null
                        )
                    }
                },
                onFailure = { exception ->
                    _viewState.update {
                        it.copy(isLoading = false, error = exception.message)
                    }
                    _effects.emit(PhotoEffect.ShowError(exception.message ?: "Search failed"))
                }
            )
        }
    }

    private fun loadMorePhotos() {
        val currentState = _viewState.value
        if (currentState.isLoadingMore || !currentState.hasMorePages) return

        viewModelScope.launch {
            _viewState.update { it.copy(isLoadingMore = true) }

            val nextPage = currentState.currentPage + 1
            val result = if (currentState.searchQuery.isNotBlank()) {
                searchPhotosUseCase(currentState.searchQuery, nextPage)
            } else {
                getCuratedPhotosUseCase(nextPage)
            }

            result.fold(
                onSuccess = { response ->
                    _viewState.update {
                        it.copy(
                            isLoadingMore = false,
                            photos = it.photos + response.photos,
                            currentPage = response.page,
                            hasMorePages = response.nextPage != null
                        )
                    }
                },
                onFailure = { exception ->
                    _viewState.update { it.copy(isLoadingMore = false) }
                    _effects.emit(PhotoEffect.ShowError("Failed to load more photos"))
                }
            )
        }
    }

    private fun retryLoading() {
        val currentState = _viewState.value
        if (currentState.searchQuery.isNotBlank()) {
            searchPhotos(currentState.searchQuery)
        } else {
            loadCuratedPhotos()
        }
    }

    private fun downloadPhoto(photo: Photo) {
        viewModelScope.launch {
            downloadImageUseCase(photo).fold(
                onSuccess = { filePath ->
                    _effects.emit(PhotoEffect.DownloadSuccess(filePath))
                    _effects.emit(PhotoEffect.ShowSuccess("Image downloaded successfully"))
                },
                onFailure = { exception ->
                    _effects.emit(PhotoEffect.ShowError("Failed to download image: ${exception.message}"))
                }
            )
        }
    }

    private fun setWallpaper(photo: Photo) {
        viewModelScope.launch {
            setWallpaperUseCase(photo).fold(
                onSuccess = {
                    _effects.emit(PhotoEffect.WallpaperSetSuccess("Wallpaper set successfully"))
                    _effects.emit(PhotoEffect.ShowSuccess("Wallpaper set successfully"))
                },
                onFailure = { exception ->
                    _effects.emit(PhotoEffect.ShowError("Failed to set wallpaper: ${exception.message}"))
                }
            )
        }
    }
}