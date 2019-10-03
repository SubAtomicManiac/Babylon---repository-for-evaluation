package com.example.babylon.ui

import com.example.babylon.model.Post

interface PostClickListener {
    fun onPostClicked(post: Post)
}