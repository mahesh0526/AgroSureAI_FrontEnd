package com.example.agrosureai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Alert(val title: String, val description: String, val date: String, val color: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentAlertsScreen(onBackClick: () -> Unit) {
    val alerts = listOf(
        Alert("Pest Alert: Locusts", "High risk of locust attack in your district within 3 days.", "2025-12-28", Color(0xFFFFEBDE)),
        Alert("Weather Warning: Hailstorm", "Hailstorm expected tomorrow afternoon. Take necessary precautions.", "2025-12-27", Color(0xFFE3F2FD)),
        Alert("Irrigation Advisory", "Soil moisture is low. It is advised to irrigate the fields within 2 days.", "2025-12-26", Color(0xFFE8EAF6)),
        Alert("Market Price Drop", "Soybean prices have dropped by 5% in the local market.", "2025-12-25", Color(0xFFFBE9E7))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recent Alerts") },
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
            items(alerts) { alert ->
                AlertCard(alert)
            }
        }
    }
}

@Composable
private fun AlertCard(alert: Alert) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = alert.color)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
            Icon(Icons.Default.WarningAmber, contentDescription = "Alert", tint = Color.DarkGray.copy(alpha = 0.6f))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(alert.title, fontWeight = FontWeight.Bold)
                Text(alert.description, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(alert.date, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

