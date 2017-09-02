package org.pretorh.example.kotlintest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.posts.view.*
import org.pretorh.example.kotlintest.service.Post

class PostAdapter(private val posts: List<Post>,
                  private val layoutInflater: LayoutInflater,
                  private val clickHandler: (Int) -> Unit) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onBindViewHolder(holder: PostViewHolder?, position: Int) {
        holder?.bind(posts[position])
    }

    override fun getItemCount(): Int  = posts.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder {
        val itemView = layoutInflater.inflate(R.layout.posts, parent, false)
        return PostViewHolder(itemView, clickHandler)
    }
}

class PostViewHolder(itemView: View?, private val clickHandler: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
    fun bind(post: Post) {
        itemView.txtId.text = post.id.toString()
        itemView.title.text = post.title
        itemView.setOnClickListener { clickHandler(post.id) }
    }
}
