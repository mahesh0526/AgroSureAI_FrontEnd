package com.example.agrosureai

import androidx.compose.foundation.clickable // 1. Add the missing import
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(onAcceptClick: () -> Unit, onBackClick: () -> Unit) {
    var isChecked by remember { mutableStateOf(false) }

    val terms = listOf(
        "All information provided must be accurate and verifiable.",
        "False claims may result in policy cancellation and legal action.",
        "Field verification by insurance officer may be required.",
        "Claim processing typically takes 7-14 business days.",
        "Payout amount is subject to policy terms and damage assessment.",
        "GPS coordinates and photos must match the registered farm location.",
        "You authorize the insurance company to verify all submitted information."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Claim Terms & Conditions") },
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
            Card(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Column(
                    modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
                ) {
                    Text("Claim Terms & Conditions", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    terms.forEachIndexed { index, text ->
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text("${index + 1}. ", fontWeight = FontWeight.Bold)
                            Text(text)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isChecked = !isChecked } // This will now work
                    .padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("I have read and agree to the terms and conditions. I confirm that all information provided is accurate.", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAcceptClick,
                enabled = isChecked, // Button is only enabled when checkbox is checked
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
            ) {
                Text("Accept & Submit Claim")
            }
        }
    }
}