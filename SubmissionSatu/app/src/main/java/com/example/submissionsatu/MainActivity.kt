package com.example.submissionsatu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionsatu.adapter.UserAdapter
import com.example.submissionsatu.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Github User's"

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        list_user.layoutManager = LinearLayoutManager(this)
        list_user.adapter = adapter

        mainViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(MainViewModel::class.java)

        mainViewModel.setUsers()
        mainViewModel.getUsers().observe(this, Observer { user ->
            if(user!=null){
                adapter.setData(user)
            }
        })
    }
}