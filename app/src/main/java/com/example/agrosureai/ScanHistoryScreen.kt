package com.example.agrosureai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ScanHistoryItem(val date: String, val disease: String, val damage: String, val health: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanHistoryScreen(onBackClick: () -> Unit) {
    val historyItems = listOf(
        ScanHistoryItem("16/12/2025", "Leaf Rust", "20% Damage", "80%"),
        ScanHistoryItem("14/12/2025", "Powdery Mildew", "15% Damage", "85%"),
        ScanHistoryItem("10/12/2025", "Healthy", "0% Damage", "100%")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Scan History") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(historyItems) { item ->
                ScanHistoryCard(item)
            }
        }
    }
}

@Composable
private fun ScanHistoryCard(item: ScanHistoryItem) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with placeholder image
                contentDescription = "Crop Scan Image",
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Crop Scan", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(item.date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(item.disease, fontWeight = FontWeight.Bold, color = if (item.disease == "Healthy") Color.Black else Color.Red)
                Row {
                    Text("${item.damage}", fontSize = 12.sp, color = Color.Red, modifier = Modifier.background(Color(0xFFFFEBEE)).padding(horizontal = 4.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Health: ${item.health}", fontSize = 12.sp)
                }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "View Details")
        }
    }
}
