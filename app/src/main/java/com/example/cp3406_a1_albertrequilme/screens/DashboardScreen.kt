package com.example.cp3406_a1_albertrequilme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cp3406_a1_albertrequilme.data.sampleBooks
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme
import com.example.cp3406_a1_albertrequilme.ui.theme.CustomBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navigateToBookList: () -> Unit, navigateToSuggestions: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novelkakalot", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CustomBlue)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Currently Reading", style = MaterialTheme.typography.headlineMedium, color = CustomBlue)
            sampleBooks.forEach { book ->
                BookCard(book = book)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Text("Goal Progress: 75%", color = CustomBlue)
            Button(onClick = navigateToBookList, modifier = Modifier.padding(top = 16.dp)) {
                Text("View All Books")
            }
            Button(onClick = navigateToSuggestions, modifier = Modifier.padding(top = 8.dp)) {
                Text("Get Suggestions")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    CP3406A1AlbertRequilmeTheme {
        DashboardScreen(
            navigateToBookList = {},
            navigateToSuggestions = {}  // Added this to match the function signature
        )
    }
}