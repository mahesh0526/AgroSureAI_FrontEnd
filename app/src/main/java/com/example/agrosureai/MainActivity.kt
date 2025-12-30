package com.example.agrosureai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.agrosureai.ui.theme.AgroSureAITheme
import java.io.File

sealed class Screen {
    object Splash : Screen()
    object AiCropScan : Screen()
    object RealTimeDetection : Screen()
    object SmartInsurance : Screen()
    object Login : Screen()
    object Register : Screen()
    object ForgotPassword : Screen()
    data class ResetPassword(val email: String) : Screen()
    object Home : Screen()
    // Rest of your screens
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AgroSureAITheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

                when (val screen = currentScreen) {
                    Screen.Splash -> {
                        SplashScreen(onTimeout = { currentScreen = Screen.AiCropScan }) // 1. Go to first onboarding screen
                    }
                    Screen.AiCropScan -> {
                        AiCropScanScreen(
                            onNextClick = { currentScreen = Screen.RealTimeDetection }, // 2. Go to second onboarding screen
                            onSkipClick = { currentScreen = Screen.Login } // Skip to Login
                        )
                    }
                    Screen.RealTimeDetection -> {
                        RealTimeDetectionScreen(
                            onNextClick = { currentScreen = Screen.SmartInsurance }, // 3. Go to third onboarding screen
                            onSkipClick = { currentScreen = Screen.Login } // Skip to Login
                        )
                    }
                    Screen.SmartInsurance -> {
                        SmartInsuranceScreen(onGetStartedClick = { currentScreen = Screen.Login }) // 4. Go to Login
                    }
                    Screen.Login -> {
                        LoginScreen(
                            onLoginClick = { email, password ->
                                // TODO: Add login logic here
                                currentScreen = Screen.Home // 5. Go to Home on success
                            },
                            onForgotPasswordClick = {
                                currentScreen = Screen.ForgotPassword
                            },
                            onCreateAccountClick = {
                                currentScreen = Screen.Register
                            }
                        )
                    }
                    Screen.Register -> {
                        RegisterScreen(
                            onCreateAccountClick = { name, email, password ->
                                currentScreen = Screen.Login
                            },
                            onLoginClick = {
                                currentScreen = Screen.Login
                            }
                        )
                    }
                    Screen.ForgotPassword -> {
                        ForgotPasswordScreen(
                            onSendOtpClick = { email ->
                                currentScreen = Screen.ResetPassword(email)
                            },
                            onBackToLoginClick = {
                                currentScreen = Screen.Login
                            }
                        )
                    }
                    is Screen.ResetPassword -> {
                        ResetPasswordScreen(
                            onResetPasswordClick = { otp, newPassword, confirmPassword ->
                                currentScreen = Screen.Login
                            },
                            onBackToLoginClick = {
                                currentScreen = Screen.Login
                            }
                        )
                    }
                    Screen.Home -> {
                        // The final destination
                        HomeScreen(
                            onScanCropClick = {},
                            onInsuranceClick = {},
                            onScanTabClick = {}
                        )
                    }
                }
            }
        }
    }
}
