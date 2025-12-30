package com.example.agrosureai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(
    email: String,
    onVerifyClick: () -> Unit,
    onBackToLoginClick: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    val isOtpComplete = otp.length == 4
    var countdown by remember { mutableStateOf(30) }
    var isResendEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = countdown) {
        if (countdown > 0) {
            delay(1000)
            countdown--
        } else {
            isResendEnabled = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // 🟢 Title
        Text(
            text = "Enter OTP",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "We sent a code to your email address: $email",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🔢 OTP Input
        OutlinedTextField(
            value = otp,
            onValueChange = { if (it.length <= 4) otp = it },
            label = { Text("Enter 4-Digit OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Resend OTP
        TextButton(
            onClick = {
                if (isResendEnabled) {
                    // TODO: Implement resend logic here
                    countdown = 30
                    isResendEnabled = false
                }
            },
            enabled = isResendEnabled
        ) {
            Text(
                text = if (isResendEnabled) "Resend OTP" else "Resend OTP in $countdown s",
                color = if (isResendEnabled) Color(0xFF4F7F3B) else Color.Gray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // ✅ Verify OTP Button
        Button(
            onClick = onVerifyClick, // Reverted to simple navigation
            enabled = isOtpComplete,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4F7F3B),
                disabledContainerColor = Color(0xFFA5D6A7)
            )
        ) {
            Text(
                text = "Verify OTP",
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ⬅️ Back to Login
        OutlinedButton(
            onClick = onBackToLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Back to Login",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}
