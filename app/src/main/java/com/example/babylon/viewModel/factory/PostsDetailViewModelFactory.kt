package com.example.babylon.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babylon.repository.Repository
import com.example.babylon.viewModel.PostDetailViewModel

class PostsDetailViewModelFactory(val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailViewModel(repository) as T
    }
}