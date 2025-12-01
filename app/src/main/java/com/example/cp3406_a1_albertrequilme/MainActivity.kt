package com.example.cp3406_a1_albertrequilme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cp3406_a1_albertrequilme.screens.AddEditBookScreen
import com.example.cp3406_a1_albertrequilme.screens.BookListScreen
import com.example.cp3406_a1_albertrequilme.screens.DashboardScreen
import com.example.cp3406_a1_albertrequilme.screens.SuggestionsScreen
import com.example.cp3406_a1_albertrequilme.ui.theme.CP3406A1AlbertRequilmeTheme
import android.util.Log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CP3406A1AlbertRequilmeTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("dashboard") {
                            DashboardScreen(
                                navigateToBookList = {
                                    Log.d("Navigation", "Navigating to bookList")
                                    navController.navigate("bookList")
                                },
                                navigateToSuggestions = { navController.navigate("suggestions") }
                            )
                        }
                        composable("bookList") { BookListScreen(
                            navigateToAddEdit = { navController.navigate("addEdit") },
                            viewModel = viewModel()
                        ) }
                        composable("addEdit") {
                            AddEditBookScreen(
                                navigateBack = { navController.popBackStack() },  // Added this
                                viewModel = viewModel()
                            )
                        }
                        composable("suggestions") { SuggestionsScreen { navController.navigate("bookList") } }
                        composable("addEdit") { AddEditBookScreen(
                            navigateBack = { navController.popBackStack() },
                            viewModel = viewModel()
                        ) }
                    }
                }
            }
        }
    }
}
