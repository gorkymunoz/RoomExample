package com.example.roomexample.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class BookRepository(application: Application) {

    private val bookDao: BookDao
    val allBooks: LiveData<List<BookEntity>>

    init {
        val bookDb = BookRoomDatabase.getDatabase(application)
        bookDao = bookDb!!.bookDao()
        allBooks = bookDao.allBoks
    }

    fun getBooksByBookOrAuthor(searchQuery:String): LiveData<List<BookEntity>>{
        return bookDao.getBooksByBookOrAuthor(searchQuery)
    }

    fun insert(newBook: BookEntity) {
        InsertAsyncTask(bookDao).execute(newBook)
    }

    fun update(book: BookEntity) {
        UpdateAsyncTask(bookDao).execute(book)
    }

    fun delete(book: BookEntity) {
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