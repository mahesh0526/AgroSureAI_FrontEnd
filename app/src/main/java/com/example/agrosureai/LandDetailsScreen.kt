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
fun LandDetailsScreen(onNextClick: () -> Unit, onBackClick: () -> Unit) {
    var landArea by remember { mutableStateOf("2.5") }
    
    val soilTypes = listOf("Black Soil", "Red Soil", "Alluvial Soil", "Loam")
    var soilTypeExpanded by remember { mutableStateOf(false) }
    var selectedSoilType by remember { mutableStateOf(soilTypes[0]) }

    val irrigationTypes = listOf("Drip", "Sprinkler", "Surface", "Canal")
    var irrigationTypeExpanded by remember { mutableStateOf(false) }
    var selectedIrrigationType by remember { mutableStateOf(irrigationTypes[0]) }

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
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Soil Type Dropdown
            ExposedDropdownMenuBox(
                expanded = soilTypeExpanded,
                onExpandedChange = { soilTypeExpanded = !soilTypeExpanded }
            ) {
                OutlinedTextField(
                    value = selectedSoilType,
                    onValueChange = {},
                    label = { Text("Soil Type") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = soilTypeExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = soilTypeExpanded,
                    onDismissRequest = { soilTypeExpanded = false }
                ) {
                    soilTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                selectedSoilType = type
                                soilTypeExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Irrigation Type Dropdown
            ExposedDropdownMenuBox(
                expanded = irrigationTypeExpanded,
                onExpandedChange = { irrigationTypeExpanded = !irrigationTypeExpanded }
            ) {
                OutlinedTextField(
                    value = selectedIrrigationType,
                    onValueChange = {},
                    label = { Text("Irrigation Type") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = irrigationTypeExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = irrigationTypeExpanded,
                    onDismissRequest = { irrigationTypeExpanded = false }
                ) {
                    irrigationTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                selectedIrrigationType = type
                                irrigationTypeExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onNextClick,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
            ) {
                Text("Next: Crop Details")
            }
        }
    }
}
