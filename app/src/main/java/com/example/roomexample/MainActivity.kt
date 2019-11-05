package com.example.roomexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.adapters.BookListAdapter
import com.example.roomexample.data.BookEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val bookListAdapter : BookListAdapter = BookListAdapter(this)
        rv_books.adapter = bookListAdapter
        rv_books.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent,NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val id = UUID.randomUUID().toString()
            val authorName = data!!.getStringExtra(NewBookActivity.NEW_AUTHOR)
            val bookName = data.getStringExtra(NewBookActivity.NEW_BOOK)

            val book = BookEntity(id,authorName,bookName)
            bookViewModel.insert(book)
            Toast.makeText(applicationContext,
                R.string.saved,
                Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext,
                R.string.not_saved,
                Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }
}
