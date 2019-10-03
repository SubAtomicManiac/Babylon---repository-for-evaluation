package com.example.babylon.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.babylon.MyApp
import com.example.babylon.R
import com.example.babylon.common.Constants
import com.example.babylon.di.post.DaggerPostsComponent
import com.example.babylon.di.post.PostsModule
import com.example.babylon.model.Post
import com.example.babylon.viewModel.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

@Inject
lateinit var postsViewModel: PostsViewModel
    lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()

       DaggerPostsComponent
           .builder()
           .appComponent((application as MyApp).component())
           .postsModule(PostsModule(this))
           .build()
           .inject(this)

        postsViewModel.getPosts()

        postsViewModel.getPostsObservable().observe(this, Observer {

                   postsAdapter.posts.addAll(it)
                   postsAdapter.notifyDataSetChanged()
        })

        postsViewModel.getShowProgress().observe(this, Observer {
            if (it==true){
                pb_loading.visibility = View.VISIBLE
            }else{
                pb_loading.visibility = View.GONE
            }
        })

        postsViewModel.getErrorObservable().observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

    }

    private fun setUpRecyclerView(){
         postsAdapter = PostsAdapter(mutableListOf(),object: PostClickListener{
            override fun onPostClicked(post: Post) {
                val intent = Intent(this@PostsActivity,PostDetailActivity::class.java)
                intent.putExtra(Constants.POST_ID,post.id)
                intent.putExtra(Constants.USER_ID,post.userId)
                startActivity(intent)

            }
        })

        rv_posts.layoutManager = LinearLayoutManager(this)
        rv_posts.adapter = postsAdapter
    }
}
