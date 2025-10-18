package com.example.cp3406_a1_albertrequilme.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val db = BookDatabase.getDatabase(application)
    private val dao = db.bookDao()
    private val _books = dao.getAllBooks()  // Now correctly returns LiveData<List<Book>>
    val books: LiveData<List<Book>> get() = _books  // Matches the return type

    fun insertBook(book: Book) {
        viewModelScope.launch { dao.insertBook(book) }
    }
}