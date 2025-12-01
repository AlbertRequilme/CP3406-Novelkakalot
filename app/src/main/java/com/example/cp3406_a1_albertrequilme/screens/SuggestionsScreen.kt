package com.example.cp3406_a1_albertrequilme.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cp3406_a1_albertrequilme.data.sampleBooks
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionsScreen(navigateToBookList: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Suggestions") }) }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(sampleBooks) { book ->
                BookCard(book = book)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuggestionsScreenPreview() {
    CP3406A1AlbertRequilmeTheme {
        SuggestionsScreen(navigateToBookList = {})
    }
}