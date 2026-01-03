package com.example.agrosureai

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.UploadFile
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
fun UploadEvidenceScreen(onContinueClick: () -> Unit, onBackClick: () -> Unit) {
    // 1. State to hold a list of multiple Uris
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    // 2. Use the modern, correct contract for picking multiple images
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            selectedImageUris = uris
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upload Evidence") },
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
            // Upload Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.UploadFile, contentDescription = "Upload", modifier = Modifier.size(48.dp), tint = Color(0xFF4F7F3B))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Upload Geo-Tagged Photos", fontWeight = FontWeight.Bold)
                    Text("Required for claim finalization", fontSize = 12.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(onClick = { 
                        // 3. Launch the picker to select multiple images
                        filePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) 
                    }) {
                        Text("Choose Files")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Attached Files Section
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Static placeholder for the AI Scan Result
                item {
                     Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("AI.Scan.Result.jpg", modifier = Modifier.weight(1f))
                        Icon(Icons.Default.CheckCircle, contentDescription = "Uploaded", tint = Color(0xFF4CAF50))
                    }
                }
                
                // 4. Display the list of newly selected files
                items(selectedImageUris) { uri ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(uri.lastPathSegment?.substringAfterLast("/") ?: "Selected File", modifier = Modifier.weight(1f))
                        Icon(Icons.Default.CheckCircle, contentDescription = "Uploaded", tint = Color(0xFF4CAF50))
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onContinueClick,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B)),
                enabled = selectedImageUris.isNotEmpty() // Button enabled only when files are selected
            ) {
                Text("Continue to Terms")
            }
        }
    }
}
