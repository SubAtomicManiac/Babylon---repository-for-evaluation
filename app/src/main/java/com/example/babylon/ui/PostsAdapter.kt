package com.example.babylon.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.babylon.R
import com.example.babylon.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter(val posts: MutableList<Post>,val listener: PostClickListener): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post,listener)

    }


    class PostsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
     val postTv: TextView = itemView.tv_post

        fun bind(post:Post,listener: PostClickListener){
            postTv.text = post.title

            itemView.setOnClickListener {
                listener.onPostClicked(post)
            }

        }
    }
}