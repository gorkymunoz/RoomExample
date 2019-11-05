package com.example.roomexample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun insert(book:BookEntity)

    /*@Query("SELECT * FROM books")
    fun getAllBooks():LiveData<List<BookEntity>>*/

    @get:Query("SELECT * FROM books")
    val allBoks : LiveData<List<BookEntity>>

}