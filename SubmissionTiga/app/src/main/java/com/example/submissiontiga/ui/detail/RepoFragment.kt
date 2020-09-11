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
import com.example.submissiontiga.adapter.RepoAdapter
import kotlinx.android.synthetic.main.fragment_repo.*

class RepoFragment : Fragment() {
    private lateinit var adapter: RepoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RepoAdapter()
        adapter.notifyDataSetChanged()
        list_repo.layoutManager = LinearLayoutManager(activity)
        list_repo.adapter = adapter
        activity?.let {
            val detailViewModel = ViewModelProviders.of(it).get(DetailViewModel::class.java)
            observeData(detailViewModel)
        }
    }

    private fun observeData(detailViewModel: DetailViewModel){
        detailViewModel.getRepository().observe(viewLifecycleOwner, Observer {user ->
            if(user!=null){
                adapter.setData(user)
            }
        })
    }
}