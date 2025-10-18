package com.example.cp3406_a1_albertrequilme.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insertBook(book: Book)
}