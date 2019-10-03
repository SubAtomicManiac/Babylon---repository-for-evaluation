package com.example.babylon.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.babylon.model.Post
import com.example.babylon.repository.Repository
import com.example.babylon.viewModel.PostsViewModel
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
class PostsViewModelTest {

    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    lateinit var viewModel: PostsViewModel

    private val postsObserver: Observer<List<Post>> = mock()
    private val errorObserver: Observer<String> = mock()
    private val progressObserver: Observer<Boolean> = mock()

    @Before
    fun setup(){
        viewModel = PostsViewModel(repository)
    }

    @Test
    fun getPost_Returns_Data() {


        val posts = mutableListOf(
            Post(1,
                1,
                " do re mi fa so la ti do",
                "Test 1"
            )
        )
        `when`(repository.getAllPosts()).thenReturn(Single.just(posts))

        viewModel.getPostsObservable().observeForever(postsObserver)
        viewModel.getErrorObservable().observeForever(errorObserver)
        viewModel.getShowProgress().observeForever(progressObserver)

        viewModel.getPosts()

        verify(postsObserver, times(1)).onChanged(posts)
        verify(errorObserver, times(0)).onChanged(ArgumentMatchers.anyString())
        verify(progressObserver, times(1)).onChanged(true)

    }


    @Test
    fun getPost_Returns_error() {
        val errorMessage = "Error message"


        val posts = mutableListOf(
            Post(1,
                1,
                " do re mi fa so la ti do",
                "Test 2"
            )
        )
        `when`(repository.getAllPosts()).thenReturn(Single.error(
            RuntimeException(errorMessage)
        ))

        viewModel.getPostsObservable().observeForever(postsObserver)
        viewModel.getErrorObservable().observeForever(errorObserver)
        viewModel.getShowProgress().observeForever(progressObserver)

        viewModel.getPosts()

        verify(postsObserver, times(0)).onChanged(ArgumentMatchers.any())
        verify(errorObserver, times(1)).onChanged(errorMessage)
        verify(progressObserver, times(1)).onChanged(true)
        verify(progressObserver, times(1)).onChanged(false)

    }

}