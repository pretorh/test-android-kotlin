package org.pretorh.example.kotlintest.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholder {
    @GET("/posts")
    fun posts(): Call<List<Post>>

    @GET("/posts/{id}/comments")
    fun comments(@Path("id") postId: Int): Call<List<Comment>>
}
