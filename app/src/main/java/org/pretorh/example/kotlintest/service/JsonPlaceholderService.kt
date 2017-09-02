package org.pretorh.example.kotlintest.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonPlaceholderService(baseUrl: String) {
    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    fun getPosts(parser: Parser<List<Post>>) {
        retrofit.create(JsonPlaceholder::class.java)
                .posts()
                .enqueue(parser)
    }

    fun getComments(postId: Int, parser: Parser<List<Comment>>) {
        retrofit.create(JsonPlaceholder::class.java)
                .comments(postId)
                .enqueue(parser)
    }
}

abstract class Parser<T> : Callback<T> {
    abstract fun onFailure(t: Throwable)

    abstract fun onResponse(result: T)

    abstract fun defaultValue(): T

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFailure(t ?: RuntimeException("Request failed"))
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        onResponse(response?.body() ?: defaultValue())
    }
}
