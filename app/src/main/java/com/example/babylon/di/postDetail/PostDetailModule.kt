package com.example.babylon.di.postDetail

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.babylon.repository.Repository
import com.example.babylon.ui.PostDetailActivity
import com.example.babylon.viewModel.PostDetailViewModel
import com.example.babylon.viewModel.PostsViewModel
import com.example.babylon.viewModel.factory.PostsDetailViewModelFactory
import com.example.babylon.viewModel.factory.PostsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PostDetailModule(val postDetailActivity: PostDetailActivity) {

    @Provides
    @PostDetailScope
    fun providePostDetailViewModelFactory(repository: Repository): PostsDetailViewModelFactory{
        return PostsDetailViewModelFactory(repository)
    }

    @Provides
    @PostDetailScope
    fun providePostDetailViewModel(postDetailViewModelFactory: PostsDetailViewModelFactory): PostDetailViewModel{
        return ViewModelProviders.of(postDetailActivity,postDetailViewModelFactory).get(PostDetailViewModel::class.java)
    }
}