package com.example.roomexample

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.roomexample.data.BookDao
import com.example.roomexample.data.BookEntity
import com.example.roomexample.data.BookRoomDatabase

class BookViewModel(application: Application): AndroidViewModel(application) {

    private val bookDao : BookDao

    init {
        val bookDb = BookRoomDatabase.getDatabase(application)
        bookDao = bookDb!!.bookDao()
    }

    fun insert(newBook: BookEntity){
        InsertAsyncTask(bookDao).execute(newBook)
    }


    companion object {
        private class InsertAsyncTask(private val bookDao: BookDao):
            AsyncTask<BookEntity, Void, Void>() {
            override fun doInBackground(vararg books: BookEntity): Void? {
                bookDao.insert(books[0])
                return null
            }

        }
    }
}
