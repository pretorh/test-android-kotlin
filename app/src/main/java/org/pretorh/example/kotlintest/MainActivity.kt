package org.pretorh.example.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.pretorh.example.kotlintest.service.JsonPlaceholderService
import org.pretorh.example.kotlintest.service.Post
import org.pretorh.example.kotlintest.service.ResponseHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtInfo.text = getString(R.string.loading)

        JsonPlaceholderService(getString(R.string.baseUrl))
                .getPosts(object: ResponseHandler {
                    override fun onFailure(t: Throwable) {
                        txtInfo.text = "request failed ${t.message}"
                    }

                    override fun onResponse(posts: List<Post>) {
                        txtInfo.text = "got results: ${posts.size}"
                    }
                })
    }
}
