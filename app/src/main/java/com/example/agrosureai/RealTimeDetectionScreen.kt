package com.example.agrosureai

import androidx.compose.runtime.Composable

@Composable
fun RealTimeDetectionScreen(
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    OnboardingScreen(
        imageRes = R.drawable.ic_launcher_foreground, // Replace with your actual illustration
        title = "Real-Time Detection",
        description = "Get instant feedback and analysis of your crops in real-time.",
        currentStep = 2,
        onNextClick = onNextClick,
        onSkipClick = onSkipClick
    )
}
