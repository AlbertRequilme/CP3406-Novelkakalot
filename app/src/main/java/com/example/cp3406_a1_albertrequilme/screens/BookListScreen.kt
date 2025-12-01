package com.example.cp3406_a1_albertrequilme.screens

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cp3406_a1_albertrequilme.data.BookViewModel
import com.example.cp3406_a1_albertrequilme.data.sampleBooks
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navigateToAddEdit: () -> Unit, viewModel: BookViewModel) {
    val books by viewModel.books.observeAsState(emptyList())
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("My Books") }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = navigateToAddEdit,
                text = { Text("Add Book") },
                icon = { /* You can add an Icon here if you want, or leave it empty */ }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SearchBar(
                query = query, onQueryChange = { query = it }, onSearch = { active = false },
                active = active, onActiveChange = { active = it }, modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search books...") }
            ) {}
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(items = sampleBooks) { book ->
                    BookCard(book = book)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    CP3406A1AlbertRequilmeTheme {
        // Static preview with mock data, avoiding ViewModel construction
        val mockBooks = listOf(sampleBooks.first()) // Use a single sample book for preview
        var query by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }

        Scaffold(
            topBar = { TopAppBar(title = { Text("My Books") }) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {},
                    text = { Text("Add Book") },
                    icon = { /* You can add an Icon here if you want, or leave it empty */ })
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SearchBar(
                    query = query, onQueryChange = { query = it }, onSearch = { active = false },
                    active = active, onActiveChange = { active = it }, modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search books...") }
                ) {}
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(items = mockBooks) { book ->
                        BookCard(book = book)
                    }
                }
            }
        }
    }
}