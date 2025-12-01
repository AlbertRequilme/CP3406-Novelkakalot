package com.example.cp3406_a1_albertrequilme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import android.app.Application
import com.example.cp3406_a1_albertrequilme.data.Book
import com.example.cp3406_a1_albertrequilme.data.BookViewModel
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBookScreen(navigateBack: () -> Unit, viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Book") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,  // Use Material icon
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
            Button(onClick = {
                viewModel.insertBook(Book(title = title, author = author))
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