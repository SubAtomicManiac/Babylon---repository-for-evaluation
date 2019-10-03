package com.example.babylon.di.post

import androidx.lifecycle.ViewModelProviders
import com.example.babylon.ui.PostsActivity
import com.example.babylon.repository.Repository
import com.example.babylon.viewModel.PostsViewModel
import com.example.babylon.viewModel.factory.PostsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PostsModule(val postsActivity: PostsActivity) {

    @Provides
    @PostsScope
    fun providePostsViewModelFactory(repository: Repository):PostsViewModelFactory{
        return PostsViewModelFactory(repository)
    }

    @Provides
    @PostsScope
    fun providePostsViewModel(postsViewModelFactory: PostsViewModelFactory):PostsViewModel{
        return ViewModelProviders.of(postsActivity,postsViewModelFactory).get(PostsViewModel::class.java)
    }
}