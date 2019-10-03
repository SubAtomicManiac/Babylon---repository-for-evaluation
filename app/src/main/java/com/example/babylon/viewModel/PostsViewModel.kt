package com.example.babylon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylon.model.Post
import com.example.babylon.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class PostsViewModel(private val repository: Repository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val postsObservable : MutableLiveData<List<Post>> = MutableLiveData()
    private val errorObservable : MutableLiveData<String> = MutableLiveData()
    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getPosts(){
        compositeDisposable.add(
            repository.getAllPosts()
                .doOnSubscribe { showProgress.postValue(true) }
                .doOnError {showProgress.value = false  }
                .subscribe ({
                        posts -> postsObservable.value = posts
                    showProgress.value = false},{error -> errorObservable.value = error.message.toString()})

        )
    }

    fun getPostsObservable() = postsObservable

    fun getShowProgress() = showProgress

    fun getErrorObservable() = errorObservable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}