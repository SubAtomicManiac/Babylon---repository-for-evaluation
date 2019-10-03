package com.example.babylon.repository

import com.example.babylon.model.Post
import com.example.babylon.model.PostUserComment
import io.reactivex.Single

interface Repository {
    fun getAllPosts(): Single<List<Post>>

    fun getAllDetails(postId:Int,userId:Int):Single<PostUserComment>

}