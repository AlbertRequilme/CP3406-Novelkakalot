package com.example.cp3406_a1_albertrequilme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import android.app.Application
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cp3406_a1_albertrequilme.data.Book
import com.example.cp3406_a1_albertrequilme.data.BookViewModel
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBookScreen(navigateBack: () -> Unit, viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var progress by remember { mutableStateOf(0f) }   // keep as Float (0.0–1.0)
    var rating by remember { mutableStateOf(0f) }    // keep as Float (0–5)
    var review by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Book") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Author") },
                modifier = Modifier.fillMaxWidth()
            )

            // Progress slider
            Text("Progress: ${(progress * 100).toInt()}%")
            Slider(
                value = progress,
                onValueChange = { progress = it },
                valueRange = 0f..1f,
                modifier = Modifier.fillMaxWidth()
            )

            // Rating stars + slider
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { index ->
                    val filled = index < rating.toInt()
                    Icon(
                        imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Rating: ${rating.toInt()}", style = MaterialTheme.typography.bodyMedium)
            }
            Slider(
                value = rating,
                onValueChange = { rating = it.roundToInt().toFloat() }, // snap to whole stars
                valueRange = 0f..5f,
                steps = 4,
                modifier = Modifier.fillMaxWidth()
            )

            // Review input
            OutlinedTextField(
                value = review,
                onValueChange = { review = it },
                label = { Text("Review") },
                modifier = Modifier.fillMaxWidth()
            )

            // Save button
            Button(onClick = {
                viewModel.insertBook(
                    Book(
                        title = title,
                        author = author,
                        progress = progress,   // keep as Float
                        rating = rating,
                        review = review
                    )
                )
                navigateBack()
            }) { Text("Save") }

            // ISBN scan button
            IconButton(onClick = { /* Barcode scan logic */ }) {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = "Scan ISBN"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditBookScreenPreview() {
    CP3406A1AlbertRequilmeTheme {
        val context = LocalContext.current
        AddEditBookScreen(
            navigateBack = {},
            viewModel = BookViewModel(context.applicationContext as Application)
        )
    }
}