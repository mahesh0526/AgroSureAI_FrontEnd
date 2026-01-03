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
    object CropScanning : Screen()
    data class ImageConfirmation(val imageFile: File) : Screen()
    data class ConfirmScan(val imageFile: File, val cropType: String, val date: String) : Screen()
    object Diagnosis : Screen()
    object AnalysisResult : Screen()
    object ReportSaved : Screen()
    object ClaimSummary : Screen()
    object FileClaim : Screen()
    object UploadEvidence : Screen()
    object TermsAndConditions : Screen()
    object ClaimStatus : Screen()
    object PayoutEstimate : Screen()
    object ClaimEligibility : Screen()
    data class ScanDetails(val imageFile: File) : Screen()
    object FarmLocation : Screen()
    object LandDetails : Screen()
    object CropDetails : Screen()
    object SowingDetails : Screen()
    object RecentAlerts : Screen()
    object ScanHistory : Screen()
    object MyClaims : Screen()
    object Analytics : Screen()
    object Profile : Screen()
    object EditProfile : Screen()
    object Settings : Screen()
    object MyFarmDetails : Screen()
    object HelpAndSupport : Screen()
    object PrivacyPolicy : Screen()
    object Notification : Screen() // 1. Add Notification screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AgroSureAITheme {
                var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

                when (val screen = currentScreen) {
                    Screen.Splash -> {
                        SplashScreen(onTimeout = { currentScreen = Screen.AiCropScan })
                    }
                    Screen.AiCropScan -> {
                        AiCropScanScreen(
                            onNextClick = { currentScreen = Screen.RealTimeDetection },
                            onSkipClick = { currentScreen = Screen.Login }
                        )
                    }
                    Screen.RealTimeDetection -> {
                        RealTimeDetectionScreen(
                            onNextClick = { currentScreen = Screen.SmartInsurance },
                            onSkipClick = { currentScreen = Screen.Login }
                        )
                    }
                    Screen.SmartInsurance -> {
                        SmartInsuranceScreen(onGetStartedClick = { currentScreen = Screen.Login })
                    }
                    Screen.Login -> {
                        LoginScreen(
                            onLoginClick = { _, _ -> currentScreen = Screen.Home }, 
                            onForgotPasswordClick = { currentScreen = Screen.ForgotPassword },
                            onCreateAccountClick = { currentScreen = Screen.Register }
                        )
                    }
                    Screen.Register -> {
                        RegisterScreen(
                            onCreateAccountClick = { _, _, _ -> currentScreen = Screen.FarmLocation }, 
                            onLoginClick = { currentScreen = Screen.Login }
                        )
                    }
                    Screen.ForgotPassword -> {
                        ForgotPasswordScreen(
                            onSendOtpClick = { email -> currentScreen = Screen.ResetPassword(email) },
                            onBackToLoginClick = { currentScreen = Screen.Login }
                        )
                    }
                    is Screen.ResetPassword -> {
                        ResetPasswordScreen(
                            onResetPasswordClick = { _, _ -> currentScreen = Screen.Login },
                            onBackToLoginClick = { currentScreen = Screen.Login }
                        )
                    }
                    Screen.FarmLocation -> { 
                        FarmLocationScreen(
                            onConfirmLocation = { currentScreen = Screen.LandDetails },
                            onBackClick = { currentScreen = Screen.Login } 
                        )
                    }
                    Screen.LandDetails -> { 
                        LandDetailsScreen(
                            onNextClick = { currentScreen = Screen.CropDetails },
                            onBackClick = { currentScreen = Screen.FarmLocation }
                        )
                    }
                    Screen.CropDetails -> { 
                        CropDetailsScreen(
                            onNextClick = { currentScreen = Screen.SowingDetails },
                            onBackClick = { currentScreen = Screen.LandDetails }
                        )
                    }
                    Screen.SowingDetails -> {
                         SowingDetailsScreen(
                            onNextClick = { currentScreen = Screen.Home },
                            onBackClick = { currentScreen = Screen.CropDetails } 
                        )
                    }
                    Screen.Home -> {
                        HomeScreen(
                            onScanCropClick = { currentScreen = Screen.CropScanning },
                            onInsuranceClick = { currentScreen = Screen.ClaimEligibility },
                            onScanTabClick = { currentScreen = Screen.CropScanning },
                            onViewAllAlertsClick = { currentScreen = Screen.RecentAlerts },
                            onScanHistoryClick = { currentScreen = Screen.ScanHistory },
                            onMyClaimsClick = { currentScreen = Screen.MyClaims },
                            onAnalyticsClick = { currentScreen = Screen.Analytics },
                            onProfileClick = { currentScreen = Screen.Profile },
                            onNotificationClick = { currentScreen = Screen.Notification } // 2. Pass navigation action
                        )
                    }
                    Screen.RecentAlerts -> { 
                        RecentAlertsScreen(onBackClick = { currentScreen = Screen.Home })
                    }
                    Screen.ScanHistory -> { 
                        ScanHistoryScreen(onBackClick = { currentScreen = Screen.Home })
                    }
                    Screen.MyClaims -> { 
                        MyClaimsScreen(onBackClick = { currentScreen = Screen.Home })
                    }
                    Screen.Analytics -> { 
                        AnalyticsScreen(onBackClick = { currentScreen = Screen.Home })
                    }
                    Screen.Profile -> {
                        ProfileScreen(
                            onEditProfileClick = { currentScreen = Screen.EditProfile },
                            onSettingsClick = { currentScreen = Screen.Settings },
                            onLogoutClick = { currentScreen = Screen.Login },
                            onMyFarmDetailsClick = { currentScreen = Screen.MyFarmDetails },
                            onHelpAndSupportClick = { currentScreen = Screen.HelpAndSupport },
                            onPrivacyPolicyClick = { currentScreen = Screen.PrivacyPolicy },
                            onBackClick = { currentScreen = Screen.Home }
                        )
                    }
                    Screen.EditProfile -> {
                        EditProfileScreen(
                            onSaveClick = { currentScreen = Screen.Profile },
                            onBackClick = { currentScreen = Screen.Profile }
                        )
                    }
                    Screen.Settings -> {
                        SettingsScreen(onBackClick = { currentScreen = Screen.Profile })
                    }
                    Screen.MyFarmDetails -> {
                        FarmLocationScreen(onConfirmLocation = { currentScreen = Screen.LandDetails }, onBackClick = { currentScreen = Screen.Profile })
                    }
                    Screen.HelpAndSupport -> {
                        HelpAndSupportScreen(onBackClick = { currentScreen = Screen.Profile })
                    }
                    Screen.PrivacyPolicy -> {
                        PrivacyPolicyScreen(onBackClick = { currentScreen = Screen.Profile })
                    }
                    Screen.Notification -> { // 3. Add new destination
                        NotificationScreen(onBackClick = { currentScreen = Screen.Home })
                    }
                    Screen.CropScanning -> {
                        CropScanningScreen(
                            onImageCaptured = { file -> currentScreen = Screen.ImageConfirmation(file) },
                            onCancel = { currentScreen = Screen.Home }
                        )
                    }
                    is Screen.ImageConfirmation -> {
                        ImageConfirmationScreen(
                            imageFile = screen.imageFile,
                            onRetake = { currentScreen = Screen.CropScanning },
                            onUsePhoto = { currentScreen = Screen.ScanDetails(screen.imageFile) }
                        )
                    }
                    is Screen.ScanDetails -> {
                        ScanDetailsScreen(
                            onNextClick = { cropType, date -> currentScreen = Screen.ConfirmScan(screen.imageFile, cropType, date) },
                            onBackClick = { currentScreen = Screen.ImageConfirmation(screen.imageFile) }
                        )
                    }
                    is Screen.ConfirmScan -> {
                        ConfirmScanScreen(
                            imageFile = screen.imageFile,
                            cropType = screen.cropType,
                            date = screen.date,
                            onStartDiagnosis = { currentScreen = Screen.Diagnosis }
                        )
                    }
                    Screen.Diagnosis -> {
                        DiagnosisScreen(onTimeout = { currentScreen = Screen.AnalysisResult })
                    }
                    Screen.AnalysisResult -> {
                        AnalysisResultScreen(
                            onSaveReport = { currentScreen = Screen.ReportSaved },
                            onFileClaim = { currentScreen = Screen.ClaimSummary }
                        )
                    }
                    Screen.ReportSaved -> {
                        ReportSavedScreen(onBackToHome = { currentScreen = Screen.Home })
                    }
                    Screen.ClaimEligibility -> {
                        ClaimEligibilityScreen(
                            onCompleteRequirements = { currentScreen = Screen.CropScanning },
                            onBackClick = { currentScreen = Screen.Home }
                        )
                    }
                    Screen.ClaimSummary -> {
                        ClaimSummaryScreen(
                            onProceedToClaim = { currentScreen = Screen.FileClaim },
                            onBackClick = { currentScreen = Screen.Home }
                        )
                    }
                    Screen.FileClaim -> { 
                        FileClaimScreen(
                            onNextClick = { currentScreen = Screen.UploadEvidence },
                            onBackClick = { currentScreen = Screen.ClaimSummary }
                        )
                    }
                    Screen.UploadEvidence -> { 
                        UploadEvidenceScreen(
                            onContinueClick = { currentScreen = Screen.TermsAndConditions }, 
                            onBackClick = { currentScreen = Screen.FileClaim }
                        )
                    }
                    Screen.TermsAndConditions -> { 
                        TermsAndConditionsScreen(
                            onAcceptClick = { currentScreen = Screen.ClaimStatus },
                            onBackClick = { currentScreen = Screen.UploadEvidence }
                        )
                    }
                     Screen.ClaimStatus -> { 
                        ClaimStatusScreen(
                            onViewPayoutClick = { currentScreen = Screen.PayoutEstimate },
                            onBackClick = { currentScreen = Screen.Home } 
                        )
                    }
                    Screen.PayoutEstimate -> { 
                        PayoutEstimateScreen(
                            onDoneClick = { currentScreen = Screen.Home },
                            onBackClick = { currentScreen = Screen.ClaimStatus }
                        )
                    }
                }
            }
        }
    }
}
