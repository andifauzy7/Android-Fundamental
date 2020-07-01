package com.example.submissionsatu

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var users = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Github User's"

        adapter = UserAdapter(this)
        list_user.adapter = adapter

        prepare()
        addItem()

        list_user.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, users[position].name, Toast.LENGTH_SHORT).show()
        }
    }
    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
    }
    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                dataUsername[position], dataName[position],
                dataPhoto.getResourceId(position, -1), dataCompany[position],
                dataLocation[position], dataRepository[position].toInt(),
                dataFollowers[position].toInt(), dataFollowing[position].toInt()
            )
            users.add(user)
        }
        adapter.users = users
    }
}