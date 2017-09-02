package org.pretorh.example.kotlintest.service

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholder {
    @GET("/posts")
    fun posts(): Call<List<Post>>
}
