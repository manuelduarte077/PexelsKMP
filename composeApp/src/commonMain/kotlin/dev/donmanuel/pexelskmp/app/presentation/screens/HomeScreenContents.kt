package dev.donmanuel.pexelskmp.app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.donmanuel.pexelskmp.app.presentation.screens.composables.FeatureCard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenContent(
    onBackClick: () -> Unit
) {
    androidx.compose.ui.backhandler.BackHandler(true) {
        onBackClick.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.padding(top = 8.dp))
        FeatureCard()
    }
}

