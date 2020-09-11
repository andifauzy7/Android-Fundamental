@file:Suppress("DEPRECATION")

package com.example.submissiontiga.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiontiga.R
import com.example.submissiontiga.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {
    private lateinit var adapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        list_following.layoutManager = LinearLayoutManager(activity)
        list_following.adapter = adapter

        activity?.let {
            val detailViewModel = ViewModelProviders.of(it).get(DetailViewModel::class.java)
            observeData(detailViewModel)
        }
    }

    private fun observeData(detailViewModel: DetailViewModel){
        detailViewModel.getFollowing().observe(viewLifecycleOwner, Observer {user ->
            if(user!=null){
                adapter.setData(user)
            }
        })
    }
}