package com.example.submissiondua.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissiondua.DetailActivity
import com.example.submissiondua.R
import com.example.submissiondua.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val mData = ArrayList<User>()
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView){
                username.text = user.username
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(75, 75))
                    .into(img_user)
            }
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, user.username, Toast.LENGTH_SHORT).show()
                val moveToDetail = Intent(itemView.context, DetailActivity::class.java)
                itemView.context.startActivity(moveToDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    fun setData(items: ArrayList<User>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

}