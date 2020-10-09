@file:Suppress("DEPRECATION")

package com.example.submissiontiga.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiontiga.R
import com.example.submissiontiga.adapter.UserAdapter
import com.example.submissiontiga.model.User
import kotlinx.android.synthetic.main.fragment_home.*

class FavoriteFragment : Fragment() {
    private lateinit var adapter: UserAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        list_user.layoutManager = LinearLayoutManager(requireContext())
        list_user.adapter = adapter
        favoriteViewModel.allUsers.observe(viewLifecycleOwner, Observer {users ->
            adapter.setData(users as ArrayList<User>)
        })

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Toast.makeText(requireContext(), "Favorite Touched", Toast.LENGTH_SHORT).show()
            }
        })
    }
}