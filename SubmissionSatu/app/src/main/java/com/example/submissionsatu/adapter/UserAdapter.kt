package com.example.submissionsatu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.submissionsatu.R
import com.example.submissionsatu.model.User

class UserAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = arrayListOf<User>()
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var itemView = p1
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, p2, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val user = getItem(p0) as User
        viewHolder.bind(user)
        return itemView
    }

    override fun getItem(p0: Int): Any = users[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = users.size

    private inner class ViewHolder internal constructor(view: View) {
        private val name: TextView = view.findViewById(R.id.nameUser)
        private val username: TextView = view.findViewById(R.id.usernameUser)
        private val location: TextView = view.findViewById(R.id.locationUser)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)
        internal fun bind(user: User) {
            name.text = user.name
            username.text = user.username
            location.text = user.location
            imgPhoto.setImageResource(user.avatar)
        }
    }
}