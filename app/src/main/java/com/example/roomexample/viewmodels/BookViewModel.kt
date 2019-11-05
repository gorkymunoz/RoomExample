package com.example.roomexample.viewmodels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomexample.data.BookDao
import com.example.roomexample.data.BookEntity
import com.example.roomexample.data.BookRoomDatabase

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookDao: BookDao
    val allBooks: LiveData<List<BookEntity>>

    init {
        val bookDb = BookRoomDatabase.getDatabase(application)
        bookDao = bookDb!!.bookDao()
        allBooks = bookDao.allBoks
    }

    fun insert(newBook: BookEntity) {
        InsertAsyncTask(bookDao).execute(newBook)
    }

    fun update(book: BookEntity) {
        UpdateAsyncTask(bookDao).execute(book)
    }

    fun delete(book:BookEntity){
        DeleteAsyncTask(bookDao).execute(book)
    }

    companion object {

        private class DeleteAsyncTask(private val bookDao: BookDao) :
            AsyncTask<BookEntity, Void, Void>() {
            override fun doInBackground(vararg books: BookEntity): Void? {
                bookDao.delete(books[0])
                return null
            }
        }

        private class UpdateAsyncTask(private val bookDao: BookDao) :
            AsyncTask<BookEntity, Void, Void>() {
            override fun doInBackground(vararg books: BookEntity): Void? {
                bookDao.update(books[0])
                return null
            }
        }

        private class InsertAsyncTask(private val bookDao: BookDao) :
            AsyncTask<BookEntity, Void, Void>() {
            override fun doInBackground(vararg books: BookEntity): Void? {
                bookDao.insert(books[0])
                return null
            }

        }
    }
}

