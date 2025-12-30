package com.example.agrosureai

import androidx.compose.runtime.Composable

@Composable
fun AiCropScanScreen(
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    OnboardingScreen(
        imageRes = R.drawable.ic_launcher_foreground, // Replace with your actual illustration
        title = "AI Crop Scan",
        description = "Scan your crops to detect diseases and get instant recommendations.",
        currentStep = 1,
        onNextClick = onNextClick,
        onSkipClick = onSkipClick
    )
}
