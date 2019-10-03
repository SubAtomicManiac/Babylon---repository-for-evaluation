package com.example.babylon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.babylon.MyApp
import com.example.babylon.R
import com.example.babylon.common.Constants
import com.example.babylon.di.postDetail.DaggerPostDetailComponent
import com.example.babylon.di.postDetail.PostDetailModule
import com.example.babylon.viewModel.PostDetailViewModel
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.item_post.*
import javax.inject.Inject

class PostDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var postDetailViewModel: PostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        DaggerPostDetailComponent
            .builder()
            .appComponent((application as MyApp).component())
            .postDetailModule(PostDetailModule(this))
            .build()
            .inject(this)

        val bundle = intent.extras
        if (bundle!= null){
            val postId = bundle.getInt(Constants.POST_ID)
            val userId = bundle.getInt(Constants.USER_ID)
            postDetailViewModel.getData(postId,userId)
        }

        postDetailViewModel.getAllDetailsObservable().observe(this, Observer {
            tv_title.text = it.post[0].title
            tv_author_name.text = it.user[0].username
            tv_body.text = it.post[0].body
            tv_number_of_comments.text = String.format(resources.getString(R.string.comments),it.comment.size.toString())

        })

        postDetailViewModel.getProgressObservable().observe(this, Observer {
            if (it == true){
                pb_post_detail.visibility = View.VISIBLE
            }else{
                pb_post_detail.visibility = View.GONE
            }
        })

        postDetailViewModel.getErrorObservable().observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

    }
}
