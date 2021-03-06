@file:Suppress("DEPRECATION")

package com.example.submissiondua.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiondua.R
import com.example.submissiondua.adapter.UserAdapter
import com.example.submissiondua.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        list_followers.layoutManager = LinearLayoutManager(activity)
        list_followers.adapter = adapter

        activity?.let {
            val detailViewModel = ViewModelProviders.of(it).get(DetailViewModel::class.java)
            observeData(detailViewModel)
        }
    }

    private fun observeData(detailViewModel: DetailViewModel){
        detailViewModel.getFollowers().observe(viewLifecycleOwner, Observer {user ->
            if(user!=null){
                adapter.setData(user)
            }
        })
    }
}