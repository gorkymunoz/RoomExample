package com.example.roomexample.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Insert
    fun insert(book:BookEntity)

    /*@Query("SELECT * FROM books")
    fun getAllBooks():LiveData<List<BookEntity>>*/

    @get:Query("SELECT * FROM books")
    val allBoks : LiveData<List<BookEntity>>

    @Update
    fun update(book: BookEntity)

    @Delete
    fun delete(book:BookEntity)

    @Query("SELECT * FROM books WHERE book LIKE :searchString OR author LIKE :searchString")
    fun getBooksByBookOrAuthor(searchString:String) : LiveData<List<BookEntity>>

}