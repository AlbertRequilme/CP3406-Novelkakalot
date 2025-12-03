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
import androidx.compose.ui.unit.dp
import com.example.cp3406_a1_albertrequilme.data.Book
import com.example.cp3406_a1_albertrequilme.data.BookViewModel
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBookScreen(navigateBack: () -> Unit, viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var progress by remember { mutableStateOf(0f) }
    var rating by remember { mutableStateOf(0f) }
    var review by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Book") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,  // Use Material icon
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
            Text("Progress: ${(progress * 100).toInt()}%")
            Slider(
                value = progress,
                onValueChange = { progress = it },
                valueRange = 0f..1f,
                modifier = Modifier.fillMaxWidth()
            )
            Text("Rating: ${rating}")
            Slider(
                value = rating,
                onValueChange = { rating = it },
                valueRange = 0f..5f,
                steps = 4, // allows 0,1,2,3,4,5
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = review,
                onValueChange = { review = it },
                label = { Text("Review") },
                modifier = Modifier.fillMaxWidth()
            )


            Button(onClick = {
                viewModel.insertBook(Book(
                    title = title,
                    author = author,
                    progress = (progress * 100).toInt()))
                navigateBack()
            }) { Text("Save") }
            IconButton(onClick = { /* Barcode scan logic */ }) {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,  // Use Material icon
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
            viewModel = BookViewModel(context.applicationContext as Application)  // Added ViewModel
        )
    }
}