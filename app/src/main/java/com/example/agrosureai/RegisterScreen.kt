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
fun RegisterScreen(
    onCreateAccountClick: (String, String, String) -> Unit,
    onLoginClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val isFormValid by derivedStateOf {
        name.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && isPasswordStrong(password) &&
        nameError == null && emailError == null && passwordError == null
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

        Text("Create Account", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Get started with your new account")
        
        Spacer(modifier = Modifier.height(32.dp))

        // Name Field with Validation
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = if (it.isBlank()) "Name cannot be empty" else null
            },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError != null,
            singleLine = true
        )
        nameError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field with Validation
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) "Invalid email format" else null
            },
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            isError = emailError != null,
            singleLine = true
        )
        emailError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field with Validation
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = getPasswordError(it)
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError != null,
            singleLine = true
        )
        passwordError?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onCreateAccountClick(name, email, password) },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
        ) {
            Text("Create Account", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text("Already have an account? ")
            TextButton(onClick = onLoginClick) {
                Text("Login", fontWeight = FontWeight.Bold, color = Color(0xFF4F7F3B))
            }
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
