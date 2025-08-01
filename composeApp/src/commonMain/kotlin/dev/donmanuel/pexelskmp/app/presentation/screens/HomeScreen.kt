package dev.donmanuel.pexelskmp.app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.donmanuel.pexelskmp.app.presentation.PhotoEffect
import dev.donmanuel.pexelskmp.app.presentation.intent.PhotoIntent
import dev.donmanuel.pexelskmp.app.presentation.screens.composables.HomeHeader
import dev.donmanuel.pexelskmp.app.presentation.screens.composables.PhotosGrid
import dev.donmanuel.pexelskmp.app.presentation.screens.composables.SearchBar
import dev.donmanuel.pexelskmp.app.presentation.screens.composables.ToastMessage
import dev.donmanuel.pexelskmp.app.presentation.viewmodel.PhotoViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val photoViewModel = koinViewModel<PhotoViewModel>()
    val viewState by photoViewModel.viewState.collectAsState()
    val lazyGridState = rememberLazyGridState()

    var toastMessage by remember { mutableStateOf<String?>(null) }
    var showToast by remember { mutableStateOf(false) }

    // Handle effects
    LaunchedEffect(Unit) {
        photoViewModel.effects.collect { effect ->
            when (effect) {
                is PhotoEffect.ShowSuccess -> {
                    toastMessage = effect.message
                    showToast = true
                }

                is PhotoEffect.ShowError -> {
                    toastMessage = effect.message
                    showToast = true
                }

                is PhotoEffect.DownloadSuccess -> {
                    toastMessage = "Image downloaded successfully"
                    showToast = true
                }

                is PhotoEffect.WallpaperSetSuccess -> {
                    toastMessage = "Wallpaper set successfully"
                    showToast = true
                }

                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        photoViewModel.handleIntent(PhotoIntent.LoadCuratedPhotos)
    }

    // Handle pagination when reaching the end of the list
    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                if (lastVisibleItem != null &&
                    lastVisibleItem.index >= viewState.photos.size - 3 &&
                    viewState.hasMorePages &&
                    !viewState.isLoadingMore
                ) {
                    photoViewModel.handleIntent(PhotoIntent.LoadMorePhotos)
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header Section
            HomeHeader()

            // Search Bar
            SearchBar(
                onSearch = { query ->
                    photoViewModel.handleIntent(PhotoIntent.SearchPhotos(query))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content Section
            when {
                viewState.isLoading && viewState.photos.isEmpty() -> {
                    LoadingState()
                }

                viewState.error != null && viewState.photos.isEmpty() -> {
                    ErrorState(
                        error = viewState.error,
                        onRetry = {
                            photoViewModel.handleIntent(PhotoIntent.RetryLoading)
                        }
                    )
                }

                viewState.photos.isNotEmpty() -> {
                    PhotosGrid(
                        photos = viewState.photos,
                        lazyGridState = lazyGridState,
                        isLoadingMore = viewState.isLoadingMore,
                        onDownloadPhoto = { photo ->
                            photoViewModel.handleIntent(PhotoIntent.DownloadPhoto(photo))
                        },
                        onSetWallpaper = { photo ->
                            photoViewModel.handleIntent(PhotoIntent.SetWallpaper(photo))
                        }
                    )
                }
            }

            // Toast Message
            ToastMessage(
                message = toastMessage ?: "",
                isVisible = showToast,
                onDismiss = { showToast = false }
            )
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading beautiful wallpapers...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
fun ErrorState(
    error: String?,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Oops! Something went wrong",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error ?: "Unknown error occurred",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Try Again")
            }
        }
    }
}

