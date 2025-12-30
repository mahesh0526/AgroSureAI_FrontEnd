package com.example.agrosureai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisResultScreen(onSaveReport: () -> Unit, onFileClaim: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analysis Result") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Handle back */ }) {
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
            // Action Required Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(60.dp).clip(CircleShape).background(Color(0xFFF44336).copy(alpha = 0.1f))) {
                        Text("65%", fontWeight = FontWeight.Bold, color = Color.Red)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Action Required", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Crop health is compromised", color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Detected Issue
            Text("Detected Issue", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Leaf Rust", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.weight(1f))
                        Box(modifier = Modifier.background(Color(0xFFFFEBEE), RoundedCornerShape(8.dp)).padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Text("92% Confidence", color = Color.Red, fontSize = 12.sp)
                        }
                    }
                    Text("Fungal Disease", color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Severity", fontWeight = FontWeight.Bold)
                    LinearProgressIndicator(progress = 0.4f, modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)), color = Color.Red, trackColor = Color.LightGray)
                    Text("High (40% Damage)", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.align(Alignment.End))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Recommended Treatment
            Text("Recommended Treatment", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Recommended", tint = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Apply Fungicide", fontWeight = FontWeight.Bold)
                        Text("Spray Propiconazole 25% EC @ 1ml/liter of water immediately.", fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Button(
                onClick = onSaveReport,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
            ) {
                Text("Save Report")
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(onClick = onFileClaim, modifier = Modifier.fillMaxWidth().height(52.dp)) {
                Text("File Insurance Claim", color = Color.Red)
            }
        }
    }
}
