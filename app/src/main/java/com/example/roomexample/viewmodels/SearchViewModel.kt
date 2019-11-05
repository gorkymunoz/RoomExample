package com.example.roomexample.viewmodels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomexample.data.BookDao
import com.example.roomexample.data.BookEntity
import com.example.roomexample.data.BookRepository
import com.example.roomexample.data.BookRoomDatabase

class SearchViewModel(application: Application):AndroidViewModel(application){

    private val bookRepository = BookRepository(application)

    fun getBooksByBookOrAuthor(searchQuery:String): LiveData<List<BookEntity>>?{
        return bookRepository.getBooksByBookOrAuthor(searchQuery)
    }

    fun update(book: BookEntity) {
        bookRepository.update(book)
    }

    fun delete(book: BookEntity) {
        bookRepository.delete(book)
    }

}