package com.example.babylon.repository

import com.example.babylon.model.*
import com.example.babylon.net.TypicodeService
import io.reactivex.Single
import io.reactivex.Single.zip
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val typicodeService: TypicodeService):Repository {

    override fun getAllDetails(postId:Int,userId:Int): Single<PostUserComment> {
        return zip(
            typicodeService.getIndividualPost(postId),
            typicodeService.getUser(userId),
            typicodeService.getComments(postId),
            Function3<List<Post>,List<User>,List<Comment>,PostUserComment>{
                post,users,comments->
                return@Function3 PostUserComment(post,users,comments)
            }

        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun getAllPosts(): Single<List<Post>> {
       return typicodeService.getAllPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}