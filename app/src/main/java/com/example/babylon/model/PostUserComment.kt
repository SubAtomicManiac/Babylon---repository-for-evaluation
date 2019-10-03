package com.example.babylon.model

data class PostUserComment(val post: List<Post>,val user: List<User>,val comment: List<Comment>) {
}