package com.example.babylon.di.post

import com.example.babylon.di.app.AppComponent
import com.example.babylon.ui.PostsActivity
import dagger.Component

@PostsScope
@Component(modules = [PostsModule::class],dependencies = [AppComponent::class])
interface PostsComponent {
    fun inject(postsActivity: PostsActivity)
}