package com.example.submissionsatu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_user.*

class detail_user : AppCompatActivity() {
    companion object {
        const val DETAIL_PERSON = "detail_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val person = intent.getParcelableExtra<User>(DETAIL_PERSON) as User
        imageUser.setImageResource(person.avatar)
        name.text = person.name
        username.text = person.username
        company.text = person.company
        location.text = person.location
        repository.text = person.repository.toString()
        followers.text = person.followers.toString()
        following.text = person.following.toString()
    }
}