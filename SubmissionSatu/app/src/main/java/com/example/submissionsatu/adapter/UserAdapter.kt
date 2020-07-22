package com.example.submissionsatu.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionsatu.DetailActivity
import com.example.submissionsatu.R
import com.example.submissionsatu.model.User
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val mData = ArrayList<User>()
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView){
                usernameUser.text = user.username
                nameUser.text = user.name
                locationUser.text = user.location
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(75, 75))
                    .into(img_photo)
            }
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Kamu Memilih ${user.name}", Toast.LENGTH_LONG).show()
                val moveToDetail = Intent(itemView.context, DetailActivity::class.java)
                moveToDetail.putExtra(DetailActivity.DETAIL_PERSON, user)
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