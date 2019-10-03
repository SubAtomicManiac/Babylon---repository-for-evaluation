package com.example.babylon.di.postDetail

import com.example.babylon.di.app.AppComponent
import com.example.babylon.ui.PostDetailActivity
import dagger.Component

@PostDetailScope
@Component(modules = [PostDetailModule::class],dependencies = [AppComponent::class])
interface PostDetailComponent {
    fun inject(postDetailActivity: PostDetailActivity)
}