package com.example.agrosureai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun LocationDisplayScreen(latitude: Double, longitude: Double, onConfirm: () -> Unit, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Farm Location") },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_launcher_foreground), // Placeholder icon
                        contentDescription = "Location Captured",
                        tint = Color(0xFF4F7F3B),
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Location Captured", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF3E0))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_launcher_foreground), // Placeholder check icon
                        contentDescription = "Success",
                        tint = Color(0xFF4F7F3B),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("GPS Coordinates Found", fontWeight = FontWeight.Bold)
                        Text("Lat: $latitude, Long: $longitude")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // You would use a geocoder to get this from lat/long
            Text("District", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Text("Nagpur", modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(16.dp))
            Text("State", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Text("Maharashtra", modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
            ) {
                Text("Confirm Location", color = Color.White)
            }
        }
    }
}
