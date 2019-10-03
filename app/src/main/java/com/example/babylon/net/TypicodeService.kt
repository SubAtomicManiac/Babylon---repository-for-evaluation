package com.example.babylon.net

import com.example.babylon.model.Comment
import com.example.babylon.model.Post
import com.example.babylon.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TypicodeService {

    @GET("posts")
    fun getAllPosts(): Single<List<Post>>

    @GET("posts")
    fun getIndividualPost(@Query("id") postId:Int): Single<List<Post>>

    @GET("users")
    fun getUser(@Query("id") userId:Int): Single<List<User>>

    @GET("comments")
    fun getComments(@Query("postId") postId:Int):Single<List<Comment>>


}