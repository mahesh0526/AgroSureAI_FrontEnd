package com.example.agrosureai

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import java.io.File

@Composable
fun ImageConfirmationScreen(imageFile: File, onRetake: () -> Unit, onUsePhoto: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(data = imageFile),
            contentDescription = "Captured Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = onRetake,
                modifier = Modifier.weight(1f)
            ) {
                Text("Retake")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onUsePhoto,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F7F3B))
            ) {
                Text("Use Photo")
            }
        }
    }
}
