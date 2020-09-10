@file:Suppress("DEPRECATION")

package com.example.submissiontiga.ui.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiontiga.R
import com.example.submissiontiga.adapter.UserAdapter
import com.example.submissiontiga.model.User
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {
    private lateinit var adapter: UserAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        list_user.layoutManager = LinearLayoutManager(requireContext())
        list_user.adapter = adapter

        homeViewModel.getUsers().observe(requireActivity(), Observer {user ->
            if(user!=null){
                adapter.setData(user)
                progressBar.visibility = View.INVISIBLE
            }
        })

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Toast.makeText(requireContext(), data.username, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        progressBar.visibility = View.INVISIBLE
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = resources.getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
                menu.findItem(R.id.search).collapseActionView()
                runBlocking {
                    progressBar.visibility = View.VISIBLE
                    val status = async {
                        homeViewModel.setUsers(query)
                    }
                    status.await()
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}