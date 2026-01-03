package com.example.agrosureai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpAndSupportScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Help & Support") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4F7F3B),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Frequently Asked Questions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text("How do I scan a crop?", fontWeight = FontWeight.Bold)
            Text("Navigate to the 'Scan' tab from the home screen and follow the on-screen instructions to align the crop leaf within the frame.")
            Spacer(modifier = Modifier.height(16.dp))
            Text("How do I file a claim?", fontWeight = FontWeight.Bold)
            Text("You can start the claim process from the 'Claims' tab. Ensure all your profile and farm details are complete to check for eligibility.")
            Spacer(modifier = Modifier.height(32.dp))
            Text("Contact Us", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text("If you need further assistance, please contact our support team:")
            Text("Email: support@agrosureai.com", fontWeight = FontWeight.Bold)
            Text("Phone: +1 (800) 555-AGRO", fontWeight = FontWeight.Bold)

        }
    }
}
