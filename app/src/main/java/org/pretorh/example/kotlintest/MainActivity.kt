package org.pretorh.example.kotlintest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*
import org.pretorh.example.kotlintest.service.Comment
import org.pretorh.example.kotlintest.service.JsonPlaceholderService
import org.pretorh.example.kotlintest.service.Parser
import org.pretorh.example.kotlintest.service.Post

class MainActivity : AppCompatActivity() {
    private val service: JsonPlaceholderService by lazy {
        JsonPlaceholderService(getString(R.string.baseUrl))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtInfo.text = getString(R.string.loading)

        service.getPosts(object: Parser<List<Post>>() {
                    override fun defaultValue(): List<Post> {
                        return ArrayList()
                    }

                    override fun onFailure(t: Throwable) {
                        txtInfo.text = "request failed ${t.message}"
                    }

                    override fun onResponse(result: List<Post>) {
                        txtInfo.text = getString(R.string.loaded_successfully)
                        bindPostsToList(result)
                    }
                })
    }

    private fun bindPostsToList(result: List<Post>) {
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        post_list.adapter = PostAdapter(result, layoutInflater, this::loadComments)
    }

    private fun loadComments(id: Int) {
        txtInfo.text = getString(R.string.loading)
        service.getComments(id, object: Parser<List<Comment>>() {
            override fun onFailure(t: Throwable) {
                txtInfo.text = "request failed ${t.message}"
            }

            override fun onResponse(result: List<Comment>) {
                txtInfo.text = getString(R.string.loaded_successfully)
                Log.d("MainActivity", "loaded ${result.size} comments for post $id")
            }

            override fun defaultValue(): List<Comment> {
                return ArrayList()
            }
        })
    }
}
