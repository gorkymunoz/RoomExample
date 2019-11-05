package com.example.roomexample.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.EditBookActivity
import com.example.roomexample.MainActivity
import com.example.roomexample.R
import com.example.roomexample.data.BookEntity
import kotlinx.android.synthetic.main.list_item.view.*

class BookListAdapter(private val context: Context):
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    private var bookList: List<BookEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.authorName,book.bookName,position)
        holder.setListener()
    }

    fun setBooks(books: List<BookEntity>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var pos = 0
        fun setData(author:String,book:String,position:Int) {
            itemView.tvAuthor.text = author
            itemView.tvBook.text = book
            this.pos = position
        }

        fun setListener() {
            itemView.ivRowEdit.setOnClickListener {
                val intent = Intent(context,EditBookActivity::class.java)
                intent.putExtra("id",bookList[pos].id)
                intent.putExtra("author",bookList[pos].authorName)
                intent.putExtra("book",bookList[pos].bookName)
                (context as Activity).startActivityForResult(intent,MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }
            itemView.ivRowDelete.setOnClickListener {

            }
        }
    }
}