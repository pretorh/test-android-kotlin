package org.pretorh.example.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.pretorh.example.kotlintest.service.JsonPlaceholderService
import org.pretorh.example.kotlintest.service.Parser
import org.pretorh.example.kotlintest.service.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtInfo.text = getString(R.string.loading)

        JsonPlaceholderService(getString(R.string.baseUrl))
                .getPosts(object: Parser<List<Post>>() {
                    override fun defaultValue(): List<Post> {
                        return ArrayList()
                    }

                    override fun onFailure(t: Throwable) {
                        txtInfo.text = "request failed ${t.message}"
                    }

                    override fun onResponse(result: List<Post>) {
                        txtInfo.text = "got results: ${result.size}"
                    }
                })
    }
}
