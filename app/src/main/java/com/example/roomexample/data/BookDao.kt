package com.example.roomexample.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BookDao {

    @Insert
    fun insert(book:BookEntity)

}