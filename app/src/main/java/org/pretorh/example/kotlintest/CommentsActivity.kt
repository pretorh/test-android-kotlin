package org.pretorh.example.kotlintest

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*
import org.pretorh.example.kotlintest.service.Comment
import org.pretorh.example.kotlintest.service.JsonPlaceholderService
import org.pretorh.example.kotlintest.service.Parser

class CommentsActivity : AppCompatActivity() {
    private val service: JsonPlaceholderService by lazy {
        JsonPlaceholderService(getString(R.string.baseUrl))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        val postId = intent.getIntExtra(EXTRA_POST_ID, 0)
        loadComments(postId)
    }

    private fun loadComments(id: Int) {
        txtInfo.text = getString(R.string.loading)
        service.getComments(id, object: Parser<List<Comment>>() {
            override fun onFailure(t: Throwable) {
                txtInfo.text = "request failed ${t.message}"
            }

            override fun onResponse(result: List<Comment>) {
                txtInfo.text = getString(R.string.loaded_successfully)
                val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                post_list.adapter = CommentAdapter(result, layoutInflater)
            }

            override fun defaultValue(): List<Comment> {
                return ArrayList()
            }
        })
    }

    companion object {
        private val EXTRA_POST_ID: String = "EXTRA_POST_ID"

        fun newIntent(context: Context, postId: Int): Intent {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(EXTRA_POST_ID, postId)
            return intent
        }
    }
}
