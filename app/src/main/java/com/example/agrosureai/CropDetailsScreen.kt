package com.example.agrosureai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropDetailsScreen(onNextClick: () -> Unit, onBackClick: () -> Unit) {
    var cropName by remember { mutableStateOf("") }
    var season by remember { mutableStateOf("") }

    val cropNames = listOf("Wheat", "Rice", "Maize", "Cotton", "Sugarcane")
    val seasons = listOf("Kharif (Monsoon)", "Rabi (Winter)", "Zaid (Summer)")

    var cropExpanded by remember { mutableStateOf(false) }
    var seasonExpanded by remember { mutableStateOf(false) }

    val isNextEnabled = cropName.isNotBlank() && season.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crop Details") },
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
            ExposedDropdownMenuBox(expanded = cropExpanded, onExpandedChange = { cropExpanded = !cropExpanded }) {
                OutlinedTextField(
                    value = cropName,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Crop Name") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cropExpanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(expanded = cropExpanded, onDismissRequest = { cropExpanded = false }) {
                    cropNames.forEach { name ->
                        DropdownMenuItem(text = { Text(name) }, onClick = {
                            cropName = name
                            cropExpanded = false
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = seasonExpanded, onExpandedChange = { seasonExpanded = !seasonExpanded }) {
                OutlinedTextField(
                    value = season,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Season") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = seasonExpanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(expanded = seasonExpanded, onDismissRequest = { seasonExpanded = false }) {
                    seasons.forEach { s ->
                        DropdownMenuItem(text = { Text(s) }, onClick = {
                            season = s
                            seasonExpanded = false
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onNextClick,
                enabled = isNextEnabled,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNextEnabled) Color(0xFF4F7F3B) else Color(0xFFA5D6A7),
                    disabledContainerColor = Color(0xFFA5D6A7)
                )
            ) {
                Text("Next: Sowing Details")
            }
        }
    }
}
