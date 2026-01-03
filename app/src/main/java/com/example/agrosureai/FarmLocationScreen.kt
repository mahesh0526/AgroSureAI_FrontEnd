package com.example.agrosureai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GpsFixed
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
fun FarmLocationScreen(
    onConfirmLocation: () -> Unit,
    onBackClick: () -> Unit
) {
    // State to track if GPS has been "enabled" (for UI purposes)
    var isGpsEnabled by remember { mutableStateOf(false) }

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
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 48.dp, horizontal = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.GpsFixed,
                        contentDescription = "GPS Icon",
                        modifier = Modifier.size(48.dp),
                        tint = Color(0xFF4F7F3B)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isGpsEnabled) "GPS Location Captured" else "Enable GPS to capture location",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // The button changes based on the state
                    if (!isGpsEnabled) {
                        Button(
                            onClick = { isGpsEnabled = true },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Enable GPS")
                        }
                    } else {
                        Text("Location successfully recorded.", color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onConfirmLocation,
                enabled = isGpsEnabled, // Button is only enabled after GPS is "enabled"
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4F7F3B),
                    disabledContainerColor = Color(0xFFA5D6A7)
                )
            ) {
                Text("Confirm Location")
            }
        }
    }
}
