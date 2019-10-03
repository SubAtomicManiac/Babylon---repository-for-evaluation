package com.example.babylon

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.babylon.model.Comment
import com.example.babylon.model.Post
import com.example.babylon.model.PostUserComment
import com.example.babylon.model.User
import com.example.babylon.repository.Repository
import com.example.babylon.viewModel.PostDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class PostDetailViewModelTest {

    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    lateinit var postDetailViewModel: PostDetailViewModel

    private val allDetailsObserver: Observer<PostUserComment> = mock()
    private val progressObserver: Observer<Boolean> = mock()
    private val errorObserver: Observer<String> = mock()
    private val postsList = listOf(Post(1,1,"post1","This is the body of post 1"))
    private val commentsList = listOf(Comment(1,1,"Babylon 5","babylon@hello.com","This is the body"))
    private val userList = listOf(User(1,"name 1","user 1"))


    @Before
    fun setUp(){
        postDetailViewModel = PostDetailViewModel(repository)
    }

    @Test
    fun getAllDetails(){

        val postUserComment = PostUserComment(postsList,userList,commentsList)
        `when`(repository.getAllDetails(1,2)).thenReturn(Single.just(postUserComment))

        postDetailViewModel.getAllDetailsObservable().observeForever(allDetailsObserver)
        postDetailViewModel.getProgressObservable().observeForever(progressObserver)
        postDetailViewModel.getErrorObservable().observeForever(errorObserver)

        postDetailViewModel.getData(1,2)

        verify(allDetailsObserver, times(1)).onChanged(postUserComment)
        verify(progressObserver, times(1)).onChanged(true)
        verify(errorObserver, times(0)).onChanged(ArgumentMatchers.anyString())
    }

    @Test
    fun getAllDetailsError(){
        val errorMessage = "Exception "

        `when`(repository.getAllDetails(1,2)).thenReturn(Single.error(
            RuntimeException(errorMessage)

        ))

        postDetailViewModel.getAllDetailsObservable().observeForever(allDetailsObserver)
        postDetailViewModel.getProgressObservable().observeForever(progressObserver)
        postDetailViewModel.getErrorObservable().observeForever(errorObserver)

        postDetailViewModel.getData(1,2)

        verify(allDetailsObserver, times(0)).onChanged(ArgumentMatchers.any())
        verify(progressObserver, times(1)).onChanged(true)
        verify(errorObserver, times(1)).onChanged(errorMessage)
        verify(progressObserver, times(1)).onChanged(false)


    }


}