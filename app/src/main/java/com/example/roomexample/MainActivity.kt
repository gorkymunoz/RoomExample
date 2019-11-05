package com.example.roomexample

import android.app.Activity
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.adapters.BookListAdapter
import com.example.roomexample.data.BookEntity
import com.example.roomexample.viewmodels.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity(), BookListAdapter.OnDeleteClickListener {

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val bookListAdapter = BookListAdapter(this,this)
        rv_books.adapter = bookListAdapter
        rv_books.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            val intent = Intent(this, NewBookActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        bookViewModel.allBooks.observe(this, Observer { books ->
            books?.let {
                bookListAdapter.setBooks(books)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main,menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView : SearchView = menu.findItem(R.id.search).actionView as SearchView

        val componentName = ComponentName(this, SearchResultActivity::class.java)
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView.setSearchableInfo(searchableInfo)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val id = UUID.randomUUID().toString()
            val authorName = data!!.getStringExtra(NewBookActivity.NEW_AUTHOR)
            val bookName = data.getStringExtra(NewBookActivity.NEW_BOOK)

            val book = BookEntity(id, authorName, bookName)
            bookViewModel.insert(book)
            Toast.makeText(
                applicationContext,
                R.string.saved,
                Toast.LENGTH_SHORT
            ).show()
        } else if(requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val id = data!!.getStringExtra(EditBookActivity.ID)
            val authorName = data.getStringExtra(EditBookActivity.UPDATED_AUTHOR)
            val bookName = data.getStringExtra(EditBookActivity.UPDATED_BOOK)
            val book = BookEntity(id,authorName,bookName)
            bookViewModel.update(book)
            Toast.makeText(
                applicationContext,
                R.string.updated,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDeleteClickListener(myBook: BookEntity) {
        bookViewModel.delete(myBook)
        Toast.makeText(
            applicationContext,
            R.string.deleted,
            Toast.LENGTH_SHORT
        ).show()
    }
    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
}
