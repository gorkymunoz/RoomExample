package com.example.roomexample.viewmodels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomexample.data.BookDao
import com.example.roomexample.data.BookEntity
import com.example.roomexample.data.BookRepository
import com.example.roomexample.data.BookRoomDatabase

class BookViewModel(application: Application) : AndroidViewModel(application) {

    val allBooks: LiveData<List<BookEntity>>
    private val bookRepository = BookRepository(application)

    init {

        allBooks = bookRepository.allBooks
    }

    fun insert(newBook: BookEntity) {
        bookRepository.insert(newBook)
    }

    fun update(book: BookEntity) {
        bookRepository.update(book)
    }

    fun delete(book:BookEntity){
        bookRepository.delete(book)
    }
}

