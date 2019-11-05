package com.example.roomexample.viewmodels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.roomexample.data.BookDao
import com.example.roomexample.data.BookEntity
import com.example.roomexample.data.BookRoomDatabase

class SearchViewModel(application: Application):AndroidViewModel(application){

    private val bookDao: BookDao

    init {
        val bookDB = BookRoomDatabase.getDatabase(application)
        bookDao = bookDB!!.bookDao()
    }

    fun update(book: BookEntity) {
        UpdateAsyncTask(bookDao).execute(book)
    }

    fun delete(book: BookEntity) {
        DeleteAsyncTask(bookDao).execute(book)
    }

    companion object {

        private class UpdateAsyncTask(private val bookDao: BookDao) : AsyncTask<BookEntity, Void, Void>() {

            override fun doInBackground(vararg books: BookEntity): Void? {
                bookDao.update(books[0])
                return null
            }
        }

        private class DeleteAsyncTask(private val bookDao: BookDao) : AsyncTask<BookEntity, Void, Void>() {

            override fun doInBackground(vararg books: BookEntity): Void? {
                bookDao.delete(books[0])
                return null
            }
        }
    }

}