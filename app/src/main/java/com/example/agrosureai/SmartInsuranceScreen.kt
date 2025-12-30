package com.example.agrosureai

import androidx.compose.runtime.Composable

@Composable
fun SmartInsuranceScreen(
    onGetStartedClick: () -> Unit
) {
    OnboardingScreen(
        imageRes = R.drawable.ic_launcher_foreground, // Replace with your actual illustration
        title = "Smart Insurance",
        description = "Seamlessly file insurance claims with AI-verified damage reports.",
        currentStep = 3,
        onNextClick = onGetStartedClick, // The "Next" button becomes "Get Started"
        onSkipClick = {} // No skip on the last screen
    )
}
