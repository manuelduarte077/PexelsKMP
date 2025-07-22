package dev.donmanuel.pexelskmp.app.presentation.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.donmanuel.pexelskmp.app.domain.models.Photo
import org.jetbrains.compose.resources.painterResource
import pexelskmp.composeapp.generated.resources.Res
import pexelskmp.composeapp.generated.resources.ic_download
import pexelskmp.composeapp.generated.resources.ic_wallpapers

@Composable
fun PhotoCard(
    photo: Photo,
    modifier: Modifier = Modifier,
    onDownload: () -> Unit = {},
    onSetWallpaper: () -> Unit = {}
) {
    var showActionMenu by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box {
            AsyncImage(
                model = photo.src.medium,
                contentDescription = photo.alt ?: "Wallpaper",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Gradient overlay for better text readability
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.3f)
                            )
                        )
                    )
            )

            // Action button (three dots menu)
            IconButton(
                onClick = { showActionMenu = true },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(32.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(
                    text = "⋮",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Dropdown menu for actions
            DropdownMenu(
                expanded = showActionMenu,
                onDismissRequest = { showActionMenu = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            ) {
                DropdownMenuItem(
                    text = { Text("Download") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_download),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Download"
                        )
                    },
                    onClick = {
                        onDownload()
                        showActionMenu = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Set as Wallpaper") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_wallpapers),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Set as Wallpaper"
                        )
                    },
                    onClick = {
                        onSetWallpaper()
                        showActionMenu = false
                    }
                )
            }

            // Photo info at the bottom
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = photo.photographer ?: "Unknown Photographer",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (photo.alt?.isNotBlank() == true) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = photo.alt,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}