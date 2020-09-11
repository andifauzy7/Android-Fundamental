package com.example.submissiontiga.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submissiontiga.R
import com.example.submissiontiga.model.Repository
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    private val mData = ArrayList<Repository>()
    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repo: Repository) {
            with(itemView){
                name_repo.text = repo.fullName
                desc_repo.text = repo.description
                created_at.text = repo.createdAt
                lang_repo.text = repo.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(mView)
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    fun setData(items: ArrayList<Repository>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

}