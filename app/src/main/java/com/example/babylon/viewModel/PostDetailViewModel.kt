package com.example.babylon.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babylon.model.PostUserComment
import com.example.babylon.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostDetailViewModel (val repository: Repository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val detailsObservable: MutableLiveData<PostUserComment> = MutableLiveData()
    private val progressObservable: MutableLiveData<Boolean> = MutableLiveData()
    private val errorObservable: MutableLiveData<String> = MutableLiveData()



    fun getData(postId:Int,userId:Int){
        compositeDisposable.add(
            repository.getAllDetails(postId, userId)
                .doOnSubscribe { progressObservable.postValue(true) }
                .doOnError { progressObservable.value = false }
                .subscribe({data -> detailsObservable.value = data
                    progressObservable.value = false
                },{error -> errorObservable.value = error.message.toString()})

        )

    }

    fun getAllDetailsObservable() = detailsObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}