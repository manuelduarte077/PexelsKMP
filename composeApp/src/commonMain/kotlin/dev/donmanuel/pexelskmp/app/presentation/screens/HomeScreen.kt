package dev.donmanuel.pexelskmp.app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.donmanuel.pexelskmp.app.presentation.screens.composables.FeatureCard
import dev.donmanuel.pexelskmp.app.presentation.viewmodel.PhotoViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val photoViewModel = koinViewModel<PhotoViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            " Welcome to the Pexel Pexel ",
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        FeatureCard(
            viewModel = photoViewModel,
            title = "Featured Photos"
        )
    }
}