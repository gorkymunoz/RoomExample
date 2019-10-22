package com.example.roomexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class BookEntity(@PrimaryKey
                 val id:String,

                 @ColumnInfo(name = "author")
                 val authorName:String,

                 @ColumnInfo(name = "book")
                 val bookName:String){

}