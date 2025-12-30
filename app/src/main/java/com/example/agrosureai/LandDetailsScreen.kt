package com.example.agrosureai

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandDetailsScreen(onNextClick: () -> Unit, onBackClick: () -> Unit) {
    var landArea by remember { mutableStateOf("") }
    var soilType by remember { mutableStateOf("") }
    var irrigationType by remember { mutableStateOf("") }

    val soilTypes = listOf("Black Soil", "Red Soil", "Alluvial Soil", "Laterite Soil")
    val irrigationTypes = listOf("Drip", "Sprinkler", "Flood", "Canal")

    var soilExpanded by remember { mutableStateOf(false) }
    var irrigationExpanded by remember { mutableStateOf(false) }
    
    val isNextEnabled = landArea.isNotBlank() && soilType.isNotBlank() && irrigationType.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Land Details") },
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
            OutlinedTextField(
                value = landArea,
                onValueChange = { landArea = it },
                label = { Text("Land Area (Acres)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = soilExpanded, onExpandedChange = { soilExpanded = !soilExpanded }) {
                OutlinedTextField(
                    value = soilType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Soil Type") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = soilExpanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(expanded = soilExpanded, onDismissRequest = { soilExpanded = false }) {
                    soilTypes.forEach { type ->
                        DropdownMenuItem(text = { Text(type) }, onClick = {
                            soilType = type
                            soilExpanded = false
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = irrigationExpanded, onExpandedChange = { irrigationExpanded = !irrigationExpanded }) {
                OutlinedTextField(
                    value = irrigationType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Irrigation Type") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = irrigationExpanded) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(expanded = irrigationExpanded, onDismissRequest = { irrigationExpanded = false }) {
                    irrigationTypes.forEach { type ->
                        DropdownMenuItem(text = { Text(type) }, onClick = {
                            irrigationType = type
                            irrigationExpanded = false
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
                Text("Next: Crop Details")
            }
        }
    }
}
