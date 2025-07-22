package dev.donmanuel.pexelskmp.app.presentation.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.donmanuel.pexelskmp.app.domain.models.Photo
import dev.donmanuel.pexelskmp.app.presentation.intent.PhotoIntent
import dev.donmanuel.pexelskmp.app.presentation.viewmodel.PhotoViewModel

@Composable
fun FeatureCard(
    viewModel: PhotoViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(PhotoIntent.LoadCuratedPhotos)
    }

    Column {
        when {
            viewState.isLoading && viewState.photos.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            viewState.error != null && viewState.photos.isEmpty() -> {
                Text(
                    text = "Error: ${viewState.error}",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }

            viewState.photos.isNotEmpty() -> {
                PhotosList(photos = viewState.photos)
            }
        }
    }
}

@Composable
private fun PhotosList(photos: List<Photo>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(photos) { index, photo ->
            val startPadding = if (index == 0) 16.dp else 0.dp
            FeaturedItem(
                url = photo.src.medium,
                modifier = Modifier.padding(start = startPadding)
            )
        }
    }
}


@Composable
fun FeaturedItem(
    url: String,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .width(300.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = url,
            contentDescription = "Example Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
