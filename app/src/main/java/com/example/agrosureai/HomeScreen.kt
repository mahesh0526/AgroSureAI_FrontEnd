package com.example.agrosureai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onScanCropClick: () -> Unit, 
    onInsuranceClick: () -> Unit, 
    onScanTabClick: () -> Unit,
    onViewAllAlertsClick: () -> Unit,
    onScanHistoryClick: () -> Unit,
    onMyClaimsClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) } // Default to Home
    val items = listOf("Home", "Scan", "Analytics", "Claims", "Profile")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.QrCodeScanner, Icons.Filled.Analytics, Icons.Filled.Description, Icons.Filled.Person)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AgroSure AI") },
                actions = {
                    IconButton(onClick = onNotificationClick) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4F7F3B),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { 
                            when (index) {
                                1 -> onScanTabClick()
                                2 -> onAnalyticsClick()
                                3 -> onInsuranceClick()
                                4 -> onProfileClick()
                                else -> selectedItem = index
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        if (selectedItem == 0) {
            HomeContent(
                modifier = Modifier.padding(padding), 
                onScanCropClick = onScanCropClick, 
                onInsuranceClick = onInsuranceClick,
                onViewAllAlertsClick = onViewAllAlertsClick,
                onScanHistoryClick = onScanHistoryClick,
                onMyClaimsClick = onMyClaimsClick
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier, 
    onScanCropClick: () -> Unit, 
    onInsuranceClick: () -> Unit,
    onViewAllAlertsClick: () -> Unit,
    onScanHistoryClick: () -> Unit,
    onMyClaimsClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF3B5998))
        ) {
             Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("hjh", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Soybean", color = Color.White.copy(alpha = 0.8f))
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.End) {
                    Text("Policy Number", color = Color.White.copy(alpha = 0.8f))
                    Text("2454564", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text("Nagpur, Today", fontWeight = FontWeight.Bold)
                    Text("32°C", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    Text("Sunny & Clear", color = Color.Gray)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🌧️ 10% Rain")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("💨 12 km/h")
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Quick Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            QuickActionCard("Scan Crop", Icons.Filled.FilterCenterFocus, Modifier.weight(1f), onClick = onScanCropClick)
            QuickActionCard("Insurance", Icons.Filled.VerifiedUser, Modifier.weight(1f), onClick = onInsuranceClick)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Recent Alerts", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onViewAllAlertsClick) { Text("View All") }
        }
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBDE))) {
             Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.WarningAmber, contentDescription = "Pest Alert", tint = Color(0xFFE65100))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Pest Alert: Locusts", fontWeight = FontWeight.Bold)
                    Text("High risk of locust attack in your district within 3 days.", fontSize = 14.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("My Crops", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(48.dp).background(Color(0xFFEAF3E0), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                    Text("🌱")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Soybean", fontWeight = FontWeight.Bold)
                    Text("Sown: 2025-12-16", fontSize = 14.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.background(Color(0xFFC8E6C9), RoundedCornerShape(8.dp)).padding(horizontal = 12.dp, vertical = 4.dp)) {
                    Text("Active", color = Color(0xFF388E3C), fontSize = 12.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = onScanHistoryClick, modifier = Modifier.weight(1f)) {
                Icon(Icons.Filled.History, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Scan History")
            }
             OutlinedButton(onClick = onMyClaimsClick, modifier = Modifier.weight(1f)) {
                Icon(Icons.Filled.Shield, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("My Claims")
            }
        }
    }
}


@Composable
fun QuickActionCard(title: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(modifier = modifier.clickable(onClick = onClick), shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(Color(0xFFEAF3E0)), contentAlignment = Alignment.Center) { // Corrected the typo here
                 Icon(icon, contentDescription = null, tint = Color(0xFF4F7F3B))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold)
        }
    }
}
