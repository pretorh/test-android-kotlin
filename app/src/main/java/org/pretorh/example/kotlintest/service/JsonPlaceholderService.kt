package org.pretorh.example.kotlintest.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class JsonPlaceholderService(baseUrl: String) {
    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    fun getPosts(responseHandler: ResponseHandler) {
        val client = retrofit.create(JsonPlaceholder::class.java)
        return client.posts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                responseHandler.onResponse(response?.body() ?: ArrayList())
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                responseHandler.onFailure(t ?: RuntimeException("Request failed"))
            }
        })
    }
}

interface ResponseHandler {
    fun onFailure(t: Throwable)
    fun onResponse(posts: List<Post>)
}
