package com.example.submissionsatu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionsatu.model.User
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DETAIL_PERSON = "detail_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val person = intent.getParcelableExtra<User>(DETAIL_PERSON) as User
        Glide.with(this).load(person.avatar).apply(RequestOptions().override(200,200)).into(imageUser)
        name.text = person.name
        username.text = person.username
        company.text = person.company
        location.text = person.location
        repository.text = person.repository.toString()
        followers.text = person.followers.toString()
        following.text = person.following.toString()

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Detail User"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}