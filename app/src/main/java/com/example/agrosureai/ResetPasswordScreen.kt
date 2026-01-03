package com.example.agrosureai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResetPasswordScreen(
    onResetPasswordClick: (String, String) -> Unit,
    onBackToLoginClick: () -> Unit
) {
    var otp by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var otpError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    val isFormValid by derivedStateOf {
        otp.isNotBlank() && isPasswordStrong(newPassword) && newPassword == confirmPassword &&
        otpError == null && passwordError == null && confirmPasswordError == null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 🌱 App Logo
        Box(modifier = Modifier.size(100.dp).background(color = Color(0xFFEAF3E0), shape = RoundedCornerShape(16.dp)), contentAlignment = Alignment.Center) {
            Text(text = "🌱", fontSize = 48.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Reset Password", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Enter the OTP and your new password")

        Spacer(modifier = Modifier.height(32.dp))

        // OTP Field
        OutlinedTextField(
            value = otp,
            onValueChange = {
                otp = it
                otpError = if (it.isBlank()) "OTP cannot be empty" else null
            },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = otpError != null,
            singleLine = true
        )
        otpError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(16.dp))

        // New Password Field
        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                passwordError = getPasswordError(it)
                if (confirmPassword.isNotBlank()) {
                    confirmPasswordError = if (it != confirmPassword) "Passwords do not match" else null
                }
            },
            label = { Text("New Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError != null,
            singleLine = true
        )
        passwordError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = if (it != newPassword) "Passwords do not match" else null
            },
            label = { Text("Confirm New Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError != null,
            singleLine = true
        )
        confirmPasswordError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onResetPasswordClick(otp, newPassword) },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
        ) {
            Text("Reset Password", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onBackToLoginClick) {
            Text("Back to Login", color = Color(0xFF4F7F3B))
        }
    }
}

private fun isPasswordStrong(password: String): Boolean {
    return password.length >= 8 &&
           password.any { it.isUpperCase() } &&
           password.any { it.isLowerCase() } &&
           password.any { it.isDigit() } &&
           password.any { !it.isLetterOrDigit() }
}

private fun getPasswordError(password: String): String? {
    return when {
        password.length < 8 -> "Password must be at least 8 characters."
        !password.any { it.isUpperCase() } -> "Password must contain an uppercase letter."
        !password.any { it.isLowerCase() } -> "Password must contain a lowercase letter."
        !password.any { it.isDigit() } -> "Password must contain a number."
        !password.any { !it.isLetterOrDigit() } -> "Password must contain a special character."
        else -> null
    }
}
