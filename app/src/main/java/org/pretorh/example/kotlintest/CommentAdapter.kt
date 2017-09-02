package org.pretorh.example.kotlintest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.comment.view.*
import org.pretorh.example.kotlintest.service.Comment

class CommentAdapter(private val comments: List<Comment>, private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        holder?.bind(comments[position]);
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        val itemView = layoutInflater.inflate(R.layout.comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int = comments.size
}

class CommentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(comment: Comment) {
        itemView.txtId.text = comment.id.toString()
        itemView.body.text = comment.body
    }
}
