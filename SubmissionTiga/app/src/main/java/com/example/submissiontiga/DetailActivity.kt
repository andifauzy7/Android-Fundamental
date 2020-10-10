package com.example.submissiontiga

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissiontiga.db.DatabaseContract
import com.example.submissiontiga.db.UserHelper
import com.example.submissiontiga.model.User
import com.example.submissiontiga.ui.detail.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_USER = "ID_USER"
    }
    private lateinit var userHelper: UserHelper
    private lateinit var userData: User
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        val username = intent.getStringExtra(ID_USER)
        supportActionBar?.title = username
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        detailViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(
            DetailViewModel::class.java)

        username?.let { detailViewModel.setUser(it) }

        detailViewModel.getUser().observe(this, Observer {user ->
            if(user!=null){
                count_repo.text = user.countRepo.toString()
                count_followers.text = user.countFollowers.toString()
                count_following.text = user.countFollowing.toString()
                name.text = user.name
                company.text = user.company
                location.text = user.location
                bio.text = user.bio
                Glide.with(this)
                    .load(user.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(img_user)
                userData = User(user.username, user.avatar)
            }
        })

        fab.setOnClickListener {
            val values = ContentValues()
            values.put(DatabaseContract.UserColumns.USERNAME, userData.username)
            values.put(DatabaseContract.UserColumns.AVATAR, userData.avatar)
            userHelper.insert(values)
            Toast.makeText(this, resources.getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}