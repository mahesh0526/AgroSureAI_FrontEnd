package com.example.agrosureai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun ClaimStatusScreen(onBackClick: () -> Unit, onViewPayoutClick: () -> Unit) { // Added onViewPayoutClick parameter
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Claim Status") },
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
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Claim #9921", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text("Wheat - Crop Damage", color = Color.Gray)
                        }
                        Box(modifier = Modifier.background(Color(0xFFFFF9C4), RoundedCornerShape(8.dp)).padding(horizontal = 12.dp, vertical = 4.dp)) {
                            Text("Under Review", color = Color(0xFFFBC02D), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Timeline
                    Column {
                        TimelineStep(title = "Claim Submitted", subtitle = "Oct 24, 10:30 AM", isCompleted = true, isFirst = true)
                        TimelineStep(title = "AI Verification", subtitle = "In Progress", isCurrent = true)
                        TimelineStep(title = "Field Officer Visit", subtitle = "Pending")
                        TimelineStep(title = "Approval & Payout", subtitle = "Pending", isLast = true)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onViewPayoutClick, // Use the passed-in function
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("View Estimated Payout")
            }
        }
    }
}

@Composable
private fun TimelineStep(title: String, subtitle: String, isCompleted: Boolean = false, isCurrent: Boolean = false, isFirst: Boolean = false, isLast: Boolean = false) {
    Row {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // The vertical line that connects the dots
            if (!isFirst) {
                Box(modifier = Modifier.height(12.dp).width(2.dp).background(if (isCompleted || isCurrent) Color(0xFF4CAF50) else Color(0xFFE0E0E0)))
            }

            // The dot itself
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (isCompleted || isCurrent) Color(0xFF4CAF50) else Color(0xFFE0E0E0),
                        shape = CircleShape
                    )
            )

            if (!isLast) {
                Box(modifier = Modifier.height(12.dp).width(2.dp).background(if (isCompleted) Color(0xFF4CAF50) else Color(0xFFE0E0E0)))

            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.padding(bottom = if (!isLast) 24.dp else 0.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}
