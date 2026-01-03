package com.example.agrosureai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.filled.WindPower
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Forecast(val day: String, val icon: ImageVector, val temp: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(onBackClick: () -> Unit) {
    val forecasts = listOf(
        Forecast("Today", Icons.Default.WbSunny, "32°"),
        Forecast("Tue", Icons.Default.WaterDrop, "30°"),
        Forecast("Wed", Icons.Default.WaterDrop, "29°"),
        Forecast("Thu", Icons.Default.WbSunny, "31°"),
        Forecast("Fri", Icons.Default.WbSunny, "33°")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather & Analytics") },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Main Weather Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF4F7F3B))
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.Top) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("32°", fontSize = 64.sp, color = Color.White, fontWeight = FontWeight.Bold)
                            Text("Sunny", color = Color.White, fontSize = 20.sp)
                            Text("Nagpur, Maharashtra", color = Color.White.copy(alpha = 0.8f))
                        }
                        Icon(Icons.Default.WbSunny, contentDescription = "Sunny", tint = Color.Yellow, modifier = Modifier.size(64.dp))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        WeatherInfo(icon = Icons.Default.WindPower, value = "12 km/h")
                        WeatherInfo(icon = Icons.Default.WaterDrop, value = "45%")
                        WeatherInfo(icon = Icons.Default.Thermostat, value = "High UV")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5-Day Forecast Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("5-Day Forecast", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        forecasts.forEach { forecast ->
                            ForecastItem(forecast)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Analytics Options
            AnalyticsOptionCard(title = "Soil Health", subtitle = "Moisture & Nutrients", icon = Icons.AutoMirrored.Filled.TrendingUp)
            Spacer(modifier = Modifier.height(12.dp))
            AnalyticsOptionCard(title = "Risk Prediction", subtitle = "2 Active Alerts", icon = Icons.Default.WarningAmber, isWarning = true)
        }
    }
}

@Composable
private fun WeatherInfo(icon: ImageVector, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = Color.White)
        Text(value, color = Color.White)
    }
}

@Composable
private fun ForecastItem(forecast: Forecast) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(forecast.day, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Icon(forecast.icon, contentDescription = null, tint = if (forecast.day == "Today") Color(0xFFFBC02D) else Color(0xFF64B5F6), modifier = Modifier.padding(vertical = 8.dp).size(32.dp))
        Text(forecast.temp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun AnalyticsOptionCard(title: String, subtitle: String, icon: ImageVector, isWarning: Boolean = false) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = if (isWarning) Color(0xFFFFEBDE) else MaterialTheme.colorScheme.surface)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = if (isWarning) Color(0xFFE65100) else Color(0xFF4F7F3B))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
            }
            Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = "View Details")
        }
    }
}

