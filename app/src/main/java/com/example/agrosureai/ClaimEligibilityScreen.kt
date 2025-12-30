package com.example.agrosureai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimEligibilityScreen(onCompleteRequirements: () -> Unit, onBackClick: () -> Unit) {
    // This now represents the INCOMPLETE state, with hardcoded values for the UI.
    val requirements = listOf(
        "Farmer Profile Complete" to true,
        "GPS Location Captured" to true,
        "Land Details Provided" to true,
        "Sowing Proof Uploaded" to true,
        "Insurance Policy Active" to true,
        "AI Crop Scan Completed" to false // Hardcoded to show the 'incomplete' state
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Claim Eligibility") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                .verticalScroll(rememberScrollState())
        ) {
            // "Check Eligibility" Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Shield, contentDescription = "Check Eligibility", tint = Color(0xFF1565C0), modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Check Eligibility", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Verify all requirements before filing claim", fontSize = 14.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // "Requirements Checklist" Card
            Text("Requirements Checklist", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    requirements.forEachIndexed { index, (text, met) ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text, modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = if (met) Icons.Default.CheckCircle else Icons.Default.Close,
                                contentDescription = if (met) "Complete" else "Incomplete",
                                tint = if (met) Color(0xFF4CAF50) else Color.Red
                            )
                        }
                        if (index < requirements.lastIndex) {
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }
                }
            }

            // "Missing Requirements" Card
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = "Missing Requirements", tint = Color.Red)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Missing Requirements", fontWeight = FontWeight.Bold, color = Color.Red)
                        Text("Please complete: AI Crop Scan")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // "Complete Requirements First" Button
            Button(
                onClick = onCompleteRequirements,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7))
            ) {
                Text("Complete Requirements First")
            }
        }
    }
}
