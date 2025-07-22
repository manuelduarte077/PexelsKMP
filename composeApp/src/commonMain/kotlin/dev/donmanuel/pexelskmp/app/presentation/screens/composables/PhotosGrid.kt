package dev.donmanuel.pexelskmp.app.presentation.screens.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.donmanuel.pexelskmp.app.domain.models.Photo

@Composable
fun PhotosGrid(
    photos: List<Photo>,
    lazyGridState: LazyGridState,
    isLoadingMore: Boolean,
    onDownloadPhoto: (Photo) -> Unit = {},
    onSetWallpaper: (Photo) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        state = lazyGridState,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(photos) { index, photo ->
            PhotoCard(
                photo = photo,
                modifier = Modifier.aspectRatio(0.75f),
                onDownload = { onDownloadPhoto(photo) },
                onSetWallpaper = { onSetWallpaper(photo) }
            )
        }
        
        // Loading indicator at the bottom for pagination
        if (isLoadingMore) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}