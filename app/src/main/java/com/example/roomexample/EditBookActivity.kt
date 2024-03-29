package com.example.roomexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class EditBookActivity : AppCompatActivity(){

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val bundle : Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("id")
            val book = bundle.getString("book")
            val author = bundle.getString("author")

            etAuthorName.setText(author)
            etBookName.setText(book)
        }

        bCancel.setOnClickListener {
            finish()
        }

        bSave.setOnClickListener {
            val updatedAuthor = etAuthorName.text.toString()
            val updatedBook = etBookName.text.toString()
            val resultIntent = Intent()

            resultIntent.putExtra(ID,id)
            resultIntent.putExtra(UPDATED_AUTHOR,updatedAuthor)
            resultIntent.putExtra(UPDATED_BOOK,updatedBook)
            setResult(Activity.RESULT_OK,resultIntent)

            finish()
        }
    }

    companion object{
        const val ID = "book_id"
        const val UPDATED_AUTHOR = "author_name"
        const val UPDATED_BOOK = "book_name"
    }
}